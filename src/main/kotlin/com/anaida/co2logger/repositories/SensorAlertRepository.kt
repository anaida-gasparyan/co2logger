package com.anaida.co2logger.repositories

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime
import java.util.UUID

@Repository
interface SensorAlertRepository: CrudRepository<SensorAlert, Long> {

    @Modifying
    @Query("UPDATE SensorAlert se SET se.endTime = ?2 WHERE se.uuid = ?1 AND se.endTime is NULL")
    fun setAlertEndTime(uuid: UUID, endTime: OffsetDateTime);
}