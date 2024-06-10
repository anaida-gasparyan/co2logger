package com.anaida.co2logger

import com.anaida.co2logger.domain.Measurement
import com.anaida.co2logger.domain.Metrics
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import java.time.OffsetDateTime
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationIntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    @Test
    fun contextLoads() {
    }

    @Test
    fun whenGetMetrics_thenShouldReturnMetric() {
        val uuid = UUID.randomUUID()
        val result = restTemplate.getForEntity("/api/v1/sensors/$uuid/metrics", Metrics::class.java)
        assertNotNull(result)
        assertEquals(HttpStatus.OK, result.statusCode)
        assertNotNull(result.body)
        assertEquals(0, result.body?.avgLast30Days?.toInt())
        assertEquals(0, result.body?.maxLast30Days?.toInt())
    }

    @Test
    fun whePostCalled_thenShouldSaveMeasurement() {
        val uuid = UUID.randomUUID()
        val result = restTemplate.postForEntity("/api/v1/sensors/$uuid/measurements", Measurement(2000, OffsetDateTime.now()), Any::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.CREATED, result.statusCode)
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }

}
