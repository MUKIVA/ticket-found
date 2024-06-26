package com.github.mukiva.feature.airtickets.domain

data class Ticket(
    val companyName: String,
    val timeRange: List<String>,
    val price: Int
)