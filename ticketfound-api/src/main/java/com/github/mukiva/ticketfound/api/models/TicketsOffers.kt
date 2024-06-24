package com.github.mukiva.ticketfound.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsOffers(
    @SerialName("tickets_offers")
    val data: List<OfferTicketsDto>
)