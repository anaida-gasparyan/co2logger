package com.anaida.co2logger.repositories
import jakarta.persistence.*
import java.time.OffsetDateTime
import java.util.UUID

@Entity
@Table(indexes = [Index(columnList = "uuid")])
class SensorMeasurement (
    @Column(nullable = false)
    val uuid: UUID,

    val co2: Int,

    @Column(nullable = false)
    val time: OffsetDateTime,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
)