package com.github.mukiva.feature.airtickets.presentation

import java.time.LocalDateTime

enum class ClassType {
    ECONOMY
}

data class ConfigurationState(
    val startDate: LocalDateTime,
    val endDate: LocalDateTime?,
    val personCount: Int,
    val classType: ClassType,
) {
    companion object {
        fun default() = ConfigurationState(
            startDate = LocalDateTime.now(),
            endDate = null,
            personCount = 1,
            classType = ClassType.ECONOMY
        )
    }
}