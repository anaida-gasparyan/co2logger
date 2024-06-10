package com.anaida.co2logger.controller

import com.anaida.co2logger.domain.Metrics
import com.anaida.co2logger.service.SensorService
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.get
import java.util.UUID
import kotlin.test.Ignore

@WebMvcTest
class SensorsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var sensorService: SensorService

    @Test
    @Ignore
    fun givenCurrentMetrics_whenGetMetricsRequest_thenReturnsMetricsJsonWithStatus200() {
        val uuid = UUID.randomUUID()
        val metrics = Metrics(2000, 3000)
        // TODO figure out why this fails ????
        every { sensorService.getSensorMetrics(uuid) } returns metrics

        mockMvc.get("/api/v1/sensors/$uuid/metrics")
            .andExpect {
                status { status().isOk }
                content { contentType(MediaType.APPLICATION_JSON) }
                content {
                    jsonPath("$.maxLastDays").value(2000)
                    jsonPath("$.avgLastDays").value(3000)
                }
            }
    }
}