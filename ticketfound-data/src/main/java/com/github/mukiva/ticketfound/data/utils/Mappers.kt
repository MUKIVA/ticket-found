package com.github.mukiva.ticketfound.data.utils

import com.github.mukiva.ticketfound.api.models.ArrivalDto
import com.github.mukiva.ticketfound.api.models.DepartureDto
import com.github.mukiva.ticketfound.api.models.HandLuggageDto
import com.github.mukiva.ticketfound.api.models.LuggageDto
import com.github.mukiva.ticketfound.api.models.OfferDto
import com.github.mukiva.ticketfound.api.models.OfferTicketsDto
import com.github.mukiva.ticketfound.api.models.OffersDto
import com.github.mukiva.ticketfound.api.models.TicketDto
import com.github.mukiva.ticketfound.api.models.TicketsDto
import com.github.mukiva.ticketfound.api.models.TicketsOffersDto
import com.github.mukiva.ticketfound.data.models.Arrival
import com.github.mukiva.ticketfound.data.models.Departure
import com.github.mukiva.ticketfound.data.models.HandLuggage
import com.github.mukiva.ticketfound.data.models.Luggage
import com.github.mukiva.ticketfound.data.models.Offer
import com.github.mukiva.ticketfound.data.models.OfferTickets
import com.github.mukiva.ticketfound.data.models.Offers
import com.github.mukiva.ticketfound.data.models.Price
import com.github.mukiva.ticketfound.data.models.Ticket
import com.github.mukiva.ticketfound.data.models.Tickets
import com.github.mukiva.ticketfound.data.models.TicketsOffers

internal fun asDataOffers(dto: OffersDto): Offers {
    return Offers(
        data = dto.data.map(::asDataOffer)
    )
}

internal fun asDataOffer(dto: OfferDto): Offer {
    return Offer(
        id = dto.id,
        title = dto.title,
        town = dto.town,
        price = Price(dto.price.value),
    )
}

internal fun asDataTicketsOffers(dto: TicketsOffersDto): TicketsOffers {
    return TicketsOffers(
        data = dto.data.map(::asDataOfferTickets)
    )
}

internal fun asDataOfferTickets(dto: OfferTicketsDto): OfferTickets {
    return OfferTickets(
        id = dto.id,
        title = dto.title,
        timeRange = dto.timeRange,
        price = Price(dto.price.value),
    )
}

internal fun asDataTickets(dto: TicketsDto): Tickets {
    return Tickets(
        data = dto.tickets.map(::asDataTicket)
    )
}

internal fun asDataTicket(dto: TicketDto): Ticket {
    return Ticket(
        id = dto.id,
        badge = dto.badge,
        price = Price(dto.price.value),
        providerName = dto.providerName,
        company = dto.company,
        departure = asDataDeparture(dto.departure),
        arrival = asDataArrival(dto.arrival),
        hasTransfer = dto.hasTransfer,
        hasVisaTransfer = dto.hasVisaTransfer,
        luggage = asDataLuggage(dto.luggage),
        handLuggage = asDataHandLuggage(dto.handLuggage),
        isReturnable = dto.isReturnable,
        isExchangable = dto.isExchangable,
    )
}

internal fun asDataDeparture(dto: DepartureDto): Departure {
    return Departure(
        town = dto.town,
        date = dto.date,
        airport = dto.airport,
    )    
}

internal fun asDataArrival(dto: ArrivalDto): Arrival {
    return Arrival(
        town = dto.town,
        date = dto.date,
        airport = dto.airport,
    )
}

internal fun asDataLuggage(dto: LuggageDto): Luggage {
    return Luggage(
        hasLuggage = dto.hasLuggage,
        price = Price(dto.price?.value ?: 0),
    )
}

internal fun asDataHandLuggage(dto: HandLuggageDto): HandLuggage {
    return HandLuggage(
        hasHandLuggage = dto.hasHandLuggage,
        size =  dto.size ?: ""
    )
}