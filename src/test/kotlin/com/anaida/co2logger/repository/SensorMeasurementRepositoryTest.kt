package com.anaida.co2logger.repository

import com.anaida.co2logger.repositories.SensorMeasurement
import com.anaida.co2logger.repositories.SensorMeasurementRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.Limit
import java.time.OffsetDateTime
import java.util.*
import kotlin.test.assertEquals

@DataJpaTest
class SensorMeasurementRepositoryTest {

    @Autowired
    lateinit var repository: SensorMeasurementRepository

    @Test
    fun `test findByUuidOrderByTimeDesc`() {
        val uuid = UUID.randomUUID()
        repository.save(SensorMeasurement(co2 = 1090, time = OffsetDateTime.now().minusDays(10), uuid = uuid))
        val m1 = repository.save(SensorMeasurement(co2 = 3000, time = OffsetDateTime.now().minusDays(2), uuid = uuid))
        repository.save(SensorMeasurement(co2 = 3000, time = OffsetDateTime.now().minusDays(2), uuid = UUID.randomUUID()))
        val m2 = repository.save(SensorMeasurement(co2 = 5000, time = OffsetDateTime.now().minusDays(3), uuid = uuid))
        repository.save(SensorMeasurement(co2 = 6000, time = OffsetDateTime.now().minusDays(35), uuid = uuid))

        val sensorMeasurements = repository.findByUuidOrderByTimeDesc(uuid, Limit.of(2))
        assertEquals(2, sensorMeasurements.size, "2 Measurements loaded")
        assertEquals(m1.id, sensorMeasurements.get(0).id, "Correct Measurement loaded")
        assertEquals(m2.id, sensorMeasurements.get(1).id, "Correct Measurement loaded")
    }

    @Test
    fun `test averageAndMaxByUuidAndTimeAfter`() {

        val uuid = UUID.randomUUID()
        repository.save(SensorMeasurement(co2 = 1090, time = OffsetDateTime.now().minusDays(1), uuid = uuid))
        repository.save(SensorMeasurement(co2 = 3000, time = OffsetDateTime.now().minusDays(2), uuid = uuid))
        repository.save(SensorMeasurement(co2 = 5000, time = OffsetDateTime.now().minusDays(3), uuid = uuid))
        repository.save(SensorMeasurement(co2 = 6000, time = OffsetDateTime.now().minusDays(35), uuid = uuid))

        val metrics = repository.averageAndMaxByUuidAndTimeAfter(uuid, OffsetDateTime.now().minusDays(30))

        assertEquals( 3030.0, metrics.avgLast30Days.toDouble(), "The average is 3030")
        assertEquals(5000.0, metrics.maxLast30Days.toDouble(), "The max is 500")
    }
}