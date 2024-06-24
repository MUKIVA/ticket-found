package com.github.mukiva.ticketfound.api.models

import com.github.mukiva.ticketfound.api.utils.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Departure(
    @SerialName("town")
    val town: String,
    @SerialName("date")
    @Serializable(with = LocalDateTimeSerializer::class)
    val date: LocalDateTime,
    @SerialName("airport")
    val airport: String
)