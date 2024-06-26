package com.github.mukiva.feature.airtickets.domain.usecase

import com.github.mukiva.feature.airtickets.domain.Ticket
import com.github.mukiva.ticketfound.data.ITicketsOffersRepository
import com.github.mukiva.ticketfound.data.models.OfferTickets
import com.github.mukiva.ticketfound.data.utils.RequestResult
import com.github.mukiva.ticketfound.data.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTicketListUseCase @Inject constructor(
    private val ticketsOffersRepository: ITicketsOffersRepository
) {

    operator fun invoke(): Flow<RequestResult<List<Ticket>>> {
        return ticketsOffersRepository.getTicketsOffers()
            .map { requestResult ->
                requestResult.map { ticketsOffers ->
                    ticketsOffers.map(::offerTicketsAsTicket)
                }
            }
    }

    private fun offerTicketsAsTicket(
        offerTickets: OfferTickets
    ): Ticket {
        return Ticket(
            companyName = offerTickets.title,
            timeRange = offerTickets.timeRange,
            price = offerTickets.price.value,
        )
    }
}