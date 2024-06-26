package com.github.mukiva.feature.airtickets.domain

data class Ticket(
    val id: Int,
    val companyName: String,
    val timeRange: List<String>,
    val price: Int
)