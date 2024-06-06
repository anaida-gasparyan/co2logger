package com.anaida.co2logger

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Co2LoggerApplication

fun main(args: Array<String>) {
    runApplication<Co2LoggerApplication>(*args)
}
