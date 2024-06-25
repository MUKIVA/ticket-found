package com.github.mukiva.feature.airtickets.domain

data class Offer(
    val id: Int,
    val title: String,
    val town: String,
    val price: Int
)