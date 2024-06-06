package com.anaida.co2logger.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SensorController {

    @GetMapping("/api/v1/sensors/{uuid}")
    fun getSensorStatusById(@PathVariable uuid: String): Nothing = throw NotImplementedError("A method is not implemented")

    @PostMapping("/api/v1/sensors/{uuid}/measurements")
    fun saveMeasurement(@PathVariable uuid: String, @RequestBody measurement: Any) : Nothing = throw NotImplementedError("A method is not implemented")

    @GetMapping("/api/v1/sensors/{uuid}/metrics")
    fun getMetrics(@PathVariable uuid: String): Any = throw NotImplementedError("A method is not implemented")
}