package com.anaida.co2logger.controller

import com.anaida.co2logger.domain.Measurement
import com.anaida.co2logger.domain.Metrics
import com.anaida.co2logger.domain.Status
import com.anaida.co2logger.service.SensorService
import jakarta.validation.Valid
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

private val logger = KotlinLogging.logger {}

@Validated
@RestController
@RequestMapping("/api/v1/sensors")
class SensorsController(private val sensorService: SensorService) {

    @GetMapping("/{uuid}")
    fun getSensorStatusById(@PathVariable uuid: UUID): Status {
        return sensorService.getSensorStatus(uuid)
    }

    @PostMapping("/{uuid}/measurements")
    @ResponseStatus(HttpStatus.CREATED)
    fun saveMeasurement(@PathVariable uuid: UUID, @RequestBody @Valid measurement: Measurement) {
        sensorService.saveSensorMeasurement(uuid, measurement)
    }

    @GetMapping("/{uuid}/metrics")
    fun getMetrics(@PathVariable uuid: UUID): Metrics {
        return sensorService.getSensorMetrics(uuid)
    }
}