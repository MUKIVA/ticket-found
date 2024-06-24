package com.github.mukiva.ticketfound.api.models

import kotlinx.serialization.Serializable

@Serializable
data class OfferTicketsDto(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Price
)

