package com.anaida.co2logger.repositories

import jakarta.persistence.*
import java.time.OffsetDateTime
import java.util.*

@Entity
@Table(indexes = [Index(columnList = "uuid")])
class SensorAlert (
    @Column(nullable = false)
    val uuid: UUID,

    @Column(nullable = false)
    val startTime: OffsetDateTime,

    @Column(nullable = true)
    val endTime: OffsetDateTime? = null,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
)