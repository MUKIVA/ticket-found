package com.github.mukiva.ticketfound.data.models

import com.github.mukiva.ticketfound.api.utils.LocalDateTimeSerializer
import java.time.LocalDateTime

data class Arrival(
    val town: String,
    val date: LocalDateTime,
    val airport: String
)