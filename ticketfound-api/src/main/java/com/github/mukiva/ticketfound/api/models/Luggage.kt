package com.github.mukiva.ticketfound.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Luggage(
    @SerialName("has_luggage")
    val hasLuggage: Boolean,
    @SerialName("price")
    val price: Price
)