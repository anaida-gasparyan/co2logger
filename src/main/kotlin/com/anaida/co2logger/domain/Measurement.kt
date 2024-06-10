package com.anaida.co2logger.domain

import com.anaida.co2logger.repositories.SensorMeasurement
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Positive
import java.time.OffsetDateTime
import java.util.UUID

data class Measurement (
    @field:Positive(message = CO2_VALIDATION_MESSAGE)
    val co2: Int,

    @field:NotNull
    @field:PastOrPresent(message = TIME_VALIDATION_MESSAGE)
    val time: OffsetDateTime,

) {
    companion object {
        const val CO2_VALIDATION_MESSAGE: String = "CO2 measurement should be a positive value"
        const val TIME_VALIDATION_MESSAGE: String = "Time cannot be in the future"
    }
}

fun Measurement.toEntity(uuid: UUID) = SensorMeasurement(uuid = uuid, co2 = co2, time = time)