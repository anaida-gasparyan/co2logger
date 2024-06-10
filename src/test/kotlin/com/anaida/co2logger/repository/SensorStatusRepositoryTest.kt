package com.anaida.co2logger.repository

import com.anaida.co2logger.repositories.SensorStatusRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class SensorStatusRepositoryTest {

    @Autowired
    lateinit var repository: SensorStatusRepository
}