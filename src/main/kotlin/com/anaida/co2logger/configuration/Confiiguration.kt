package com.anaida.co2logger.configuration

import com.anaida.co2logger.service.SensorService
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {

    @Bean
    fun databaseInitializer(sensorService: SensorService) = ApplicationRunner {
//        val uuid1 = UUID.fromString("aaaaaaaa-6c21-4a10-8ae7-9825599143fc")
//        sensorService.saveSensorMeasurement(uuid1, Measurement(203, OffsetDateTime.now().minusDays(35)))
//        sensorService.saveSensorMeasurement(uuid1, Measurement( 2001, OffsetDateTime.now().minusDays(20)))
//        sensorService.saveSensorMeasurement(uuid1, Measurement( 201, OffsetDateTime.now().minusDays(25)))
//        sensorService.saveSensorMeasurement(uuid1, Measurement( 208, OffsetDateTime.now().minusDays(5)))
//        sensorService.saveSensorMeasurement(uuid1, Measurement(2000, OffsetDateTime.now()))
//        val uuid2 = UUID.fromString("bbbbbbbb-6c21-4a10-8ae7-9825599143fc")
//        sensorService.saveSensorMeasurement(uuid2, Measurement(3030, OffsetDateTime.now().minusDays(35)))
//        sensorService.saveSensorMeasurement(uuid2, Measurement(2001, OffsetDateTime.now().minusDays(25)))
//        sensorService.saveSensorMeasurement(uuid2, Measurement(301, OffsetDateTime.now().minusDays(20)))
//        sensorService.saveSensorMeasurement(uuid2, Measurement( 2080, OffsetDateTime.now().minusDays(15)))
//        sensorService.saveSensorMeasurement(uuid2, Measurement( 2180, OffsetDateTime.now().minusDays(10)))
//        sensorService.saveSensorMeasurement(uuid2, Measurement( 2090, OffsetDateTime.now().minusDays(9)))
//        sensorService.saveSensorMeasurement(uuid2, Measurement(300, OffsetDateTime.now()))
//        sensorService.saveSensorMeasurement(uuid2, Measurement(300, OffsetDateTime.now()))
//        sensorService.saveSensorMeasurement(uuid2, Measurement(300, OffsetDateTime.now()))
//        sensorService.saveSensorMeasurement(uuid2, Measurement(300, OffsetDateTime.now()))
    }
}