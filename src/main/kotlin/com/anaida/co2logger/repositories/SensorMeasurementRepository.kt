package com.anaida.co2logger.repositories

import com.anaida.co2logger.domain.Metrics
import org.springframework.data.domain.Limit
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime
import java.util.UUID

@Repository
interface SensorMeasurementRepository: CrudRepository<SensorMeasurement, Long> {
    @Query("""
        SELECT new com.anaida.co2logger.domain.Metrics(COALESCE(MAX(m.co2), 0.0), COALESCE(AVG(m.co2), 0.0))
        FROM SensorMeasurement m
        WHERE m.uuid = ?1 AND m.time >= ?2
    """)
    fun averageAndMaxByUuidAndTimeAfter(uuid: UUID, xDaysAgoDate: OffsetDateTime): Metrics

    fun findByUuidOrderByTimeDesc(uuid: UUID, limit: Limit): List<SensorMeasurement>
}