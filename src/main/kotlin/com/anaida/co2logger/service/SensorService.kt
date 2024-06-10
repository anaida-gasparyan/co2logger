package com.anaida.co2logger.service

import com.anaida.co2logger.configuration.ConfigurationProperties
import com.anaida.co2logger.domain.Measurement
import com.anaida.co2logger.domain.Metrics
import com.anaida.co2logger.domain.Status
import com.anaida.co2logger.domain.toEntity
import com.anaida.co2logger.repositories.*

import mu.KotlinLogging
import org.springframework.data.domain.Limit
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime
import java.util.UUID

private val logger = KotlinLogging.logger {}

@Service
class SensorService(private val measurementRepo: SensorMeasurementRepository,
                    private val sensorStatusRepo: SensorStatusRepository,
                    private val sensorAlertRepo: SensorAlertRepository,
                    private val configProps: ConfigurationProperties) {

    @Transactional
    fun saveSensorMeasurement(uuid: UUID, measurement: Measurement): Status {
        measurementRepo.save(measurement.toEntity(uuid))
        logger.debug { "Sensor [$uuid]: saving measurement $measurement" }

        return updateSensorStatus(uuid, measurement)
    }

    fun getSensorStatus(uuid: UUID): Status {
        // TODO optimise: load only Status instead of SensorStatus
        return sensorStatusRepo.findByUuid(uuid)?.status ?: Status.OK
    }

    private fun updateSensorStatus(uuid: UUID, measurement: Measurement):Status {
        val sensorStatus = getSensorStatus(uuid)
        // TODO optimise: better way to fill the list with empty values
        val lastXMeasurements = measurementRepo.findByUuidOrderByTimeDesc(uuid, Limit.of(configProps.alertThreshold))
            .map { it.co2 }.toMutableList().apply { while (size < configProps.alertThreshold) add(0) }

        val nextStatus = sensorStatus.nextStatus(lastXMeasurements, configProps.thresholdPpm)

        if (sensorStatus != nextStatus) {
            logger.info { "Sensor [$uuid]: status changed $sensorStatus -> $nextStatus" }
            // TODO optimise: never save status OK to optimise database space
            //  and delete the row if the new status is ok
            sensorStatusRepo.save(SensorStatus(uuid, nextStatus))

            if (nextStatus == Status.ALERT  && sensorStatus == Status.WARN) {
                logger.warn { "Sensor [$uuid]: alerting started at ${measurement.time}" }
                sensorAlertRepo.save(SensorAlert(uuid = uuid, startTime = measurement.time))
            }
            if (nextStatus == Status.OK  && sensorStatus == Status.ALERT) {
                logger.warn { "Sensor [$uuid]: alerting ending at ${measurement.time}" }
                sensorAlertRepo.setAlertEndTime(uuid, measurement.time)
            }
        }

        return nextStatus
    }

    @Transactional(readOnly = true)
    fun getSensorMetrics(uuid: UUID): Metrics {
        val xDaysAgoDateTime = OffsetDateTime.now().minusDays(configProps.metricsXDaysAgo)
        return measurementRepo.averageAndMaxByUuidAndTimeAfter(uuid, xDaysAgoDateTime)
    }
}