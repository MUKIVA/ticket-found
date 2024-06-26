package com.github.mukiva.feature.airtickets.domain.usecase

import com.github.mukiva.feature.airtickets.domain.TicketOffers
import com.github.mukiva.ticketfound.data.ITicketsRepository
import com.github.mukiva.ticketfound.data.models.Ticket
import com.github.mukiva.ticketfound.data.utils.RequestResult
import com.github.mukiva.ticketfound.data.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTicketsOffersUseCase @Inject constructor(
    private val ticketRepository: ITicketsRepository
) {

    operator fun invoke(): Flow<RequestResult<List<TicketOffers>>> {
        return ticketRepository.getTickets()
            .map { requestResult ->
                requestResult.map { tickets ->
                    tickets.map(::asDomain)
                }
            }
    }

    private fun asDomain(data: Ticket): TicketOffers {
        return TicketOffers(
            badgeInfo = data.badge,
            price = data.price.value,
            departureDate = data.departure.date,
            departureAirportCode = data.departure.airport,
            hasTransfer = data.hasTransfer,
            arrivalDate = data.arrival.date,
            arrivalAirportCode = data.arrival.airport
        )
    }

}