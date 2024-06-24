package com.github.mukiva.ticketfound.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OffersDto(
    @SerialName("offers")
    val data: List<OfferDto>
)