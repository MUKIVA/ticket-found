package com.github.mukiva.feature.airtickets.domain

import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset

data class TicketOffers(
    val price: Int,
    val badgeInfo: String?,
    val departureDate: LocalDateTime,
    val departureAirportCode: String,
    val arrivalDate: LocalDateTime,
    val arrivalAirportCode: String,
    val hasTransfer: Boolean
) {

    val flightTime
        get() = Timestamp.from(arrivalDate.toInstant(ZoneOffset.UTC)).time -
                Timestamp.from(departureDate.toInstant(ZoneOffset.UTC)).time

}