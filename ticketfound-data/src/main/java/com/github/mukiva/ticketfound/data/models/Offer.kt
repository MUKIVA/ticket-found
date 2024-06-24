package com.github.mukiva.ticketfound.data.models

data class Offer(
    val id: Int,
    val title: String,
    val town: String,
    val price: Price
)