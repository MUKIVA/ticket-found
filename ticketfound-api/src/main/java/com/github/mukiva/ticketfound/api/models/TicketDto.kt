package com.github.mukiva.ticketfound.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketDto(
    @SerialName("id")
    val id: Int,
    @SerialName("badge")
    val badge: String,
    @SerialName("price")
    val price: Price,
    @SerialName("provider_name")
    val providerName: String,
    @SerialName("company")
    val company: String,
    @SerialName("departure")
    val departure: Departure,
    @SerialName("arrival")
    val arrival: Arrival,
    @SerialName("has_transfer")
    val hasTransfer: Boolean,
    @SerialName("has_visa_transfer")
    val hasVisaTransfer: Boolean,
    @SerialName("luggage")
    val luggage: Luggage,
    @SerialName("hand_luggage")
    val handLuggage: HandLuggage,
    @SerialName("is_returnable")
    val isReturnable: Boolean,
    @SerialName("isExchangable")
    val isExchangable: Boolean

)

