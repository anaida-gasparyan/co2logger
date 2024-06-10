package com.anaida.co2logger.repository

import com.anaida.co2logger.repositories.SensorAlertRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class SensorAlertRepositoryTest {

    @Autowired
    lateinit var repository: SensorAlertRepository
}