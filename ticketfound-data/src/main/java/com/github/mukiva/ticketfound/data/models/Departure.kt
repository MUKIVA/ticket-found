package com.github.mukiva.ticketfound.data.models

import java.time.LocalDateTime

data class Departure(
    val town: String,
    val date: LocalDateTime,
    val airport: String
)