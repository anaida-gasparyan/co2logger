package com.anaida.co2logger.domain

enum class Status {
    OK {
        override fun nextStatus(lastXMeasurements: List<Int>, threshold: Int): Status {
            if (lastXMeasurements.isEmpty() || lastXMeasurements.first() < threshold) {
                return OK
            }
            return WARN
        }
    },
    WARN {
        override fun nextStatus(lastXMeasurements: List<Int>, threshold: Int): Status {
            if (lastXMeasurements.first() < threshold) {
                return OK
            }
            if (lastXMeasurements.all { it > threshold }) {
                // The status goes to ALERT after x higher than threshold measurements
                return ALERT
            }
            return WARN
        }
    },
    ALERT {
        override fun nextStatus(lastXMeasurements: List<Int>, threshold: Int): Status {
            if (lastXMeasurements.all { it < threshold }) {
                // The status goes back to OK after x lower than threshold measurements
                return OK
            }
            return ALERT
        }
    };

    abstract fun nextStatus(lastXMeasurements: List<Int>, threshold: Int): Status
}