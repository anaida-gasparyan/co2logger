package com.anaida.co2logger.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface SensorStatusRepository: CrudRepository<SensorStatus, UUID> {

    fun findByUuid(uuid: UUID): SensorStatus?
}