package com.github.mukiva.ticketfound.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HandLuggageDto(
    @SerialName("has_hand_luggage")
    val hasHandLuggage: Boolean,
    @SerialName("size")
    val size: String? = null,
)