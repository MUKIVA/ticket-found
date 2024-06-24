package com.github.mukiva.ticketfound.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HandLuggage(
    @SerialName("has_hand_luggage")
    val hasHandLuggage: Boolean,
    @SerialName("size")
    val size: String,
)