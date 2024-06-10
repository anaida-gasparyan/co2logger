package com.anaida.co2logger.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ConfigurationProperties (
    @Value("\${metrics.metrics_days:30}") metricsXDaysAgo: Number,
    @Value("\${metrics.threshold_ppm:2000}") thresholdPpm: Number,
    @Value("\${metrics.alert_threshold:3}") alertThreshold: Number
){
    val metricsXDaysAgo = metricsXDaysAgo.toLong()
    val thresholdPpm = thresholdPpm.toInt()
    val alertThreshold = alertThreshold.toInt()
}