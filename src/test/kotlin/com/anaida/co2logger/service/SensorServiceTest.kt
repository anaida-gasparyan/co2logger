package com.anaida.co2logger.service

import com.anaida.co2logger.configuration.ConfigurationProperties
import com.anaida.co2logger.domain.Measurement
import com.anaida.co2logger.domain.Status
import com.anaida.co2logger.repositories.*
import org.junit.jupiter.api.Test
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.data.domain.Limit
import java.time.OffsetDateTime
import java.util.UUID
import kotlin.test.assertEquals

class SensorServiceTest {
    val measurementRepo: SensorMeasurementRepository = mockk()
    val sensorAlertRepo: SensorAlertRepository = mockk()
    val sensorStatusRepo: SensorStatusRepository = mockk()

    val configurationProperties: ConfigurationProperties = mockk()

    val sensorService = SensorService(measurementRepo, sensorStatusRepo, sensorAlertRepo, configurationProperties);

    @Test
    fun whenGetBankAccount_thenReturnBankAccount() {
        // given
        val measurement = Measurement(1000, OffsetDateTime.now())
        val uuid = UUID.randomUUID()
        every { configurationProperties.alertThreshold } returns 3
        every { configurationProperties.thresholdPpm } returns 2000
        every { configurationProperties.metricsXDaysAgo } returns 30
        every { measurementRepo.save(any()) } returns SensorMeasurement(uuid, measurement.co2, measurement.time, 1);
        every { measurementRepo.findByUuidOrderByTimeDesc(uuid, Limit.of(3)) } returns listOf()
        every { sensorStatusRepo.findByUuid(uuid) } returns SensorStatus(uuid, Status.OK)

        //when
        val result = sensorService.saveSensorMeasurement(uuid, measurement);

        //then
        verify(exactly = 1) { measurementRepo.save(any()) }
        verify(exactly = 1) { measurementRepo.findByUuidOrderByTimeDesc(uuid, Limit.of(3)) }
        verify(exactly = 1) { sensorStatusRepo.findByUuid(uuid) }
        assertEquals(Status.OK, result)
    }
}