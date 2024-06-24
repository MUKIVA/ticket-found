package com.github.mukiva.ticketfound.data.models

data class OfferTickets(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Price
)

