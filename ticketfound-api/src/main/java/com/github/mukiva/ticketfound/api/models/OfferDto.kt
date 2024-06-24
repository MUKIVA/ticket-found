package com.github.mukiva.ticketfound.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfferDto(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("town")
    val town: String,
    @SerialName("price")
    val price: Price
)