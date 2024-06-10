package com.anaida.co2logger.repositories

import com.anaida.co2logger.domain.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import java.util.*

@Entity
class SensorStatus (

    @Id @Column(nullable = false)
    val uuid: UUID,

    @Enumerated(EnumType.STRING) @Column(nullable = false)
    var status: Status
)