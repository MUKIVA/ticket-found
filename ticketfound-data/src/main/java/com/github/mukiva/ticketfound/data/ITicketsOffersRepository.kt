package com.github.mukiva.ticketfound.data

import com.github.mukiva.ticketfound.api.ITicketFoundApi
import com.github.mukiva.ticketfound.data.models.OfferTickets
import com.github.mukiva.ticketfound.data.utils.RequestResult
import com.github.mukiva.ticketfound.data.utils.asDataTicketsOffers
import com.github.mukiva.ticketfound.data.utils.asRequestResult
import com.github.mukiva.ticketfound.data.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

interface ITicketsOffersRepository {
    fun getTicketsOffers(): Flow<RequestResult<List<OfferTickets>>>
}

internal class TicketsOffersRepositoryImpl(
    private val ticketFoundMockApi: ITicketFoundApi
) : ITicketsOffersRepository {

    override fun getTicketsOffers(): Flow<RequestResult<List<OfferTickets>>> {
        return getRemoteTicketsOffers()
        // TODO Implement room cache
    }

    private fun getRemoteTicketsOffers(): Flow<RequestResult<List<OfferTickets>>> {
        val start = flowOf(RequestResult.InProgress<List<OfferTickets>>())
        val request = flowOf(ticketFoundMockApi.getTicketsOffers())
            .map { result -> result.asRequestResult() }
            .map { requestResult -> requestResult.map(::asDataTicketsOffers) }
            .map { requestResult -> requestResult.map { ticketsOffers -> ticketsOffers.data } }

        return merge(start, request)
    }
}

fun createTicketsOffersRepository(
    api: ITicketFoundApi
) : ITicketsOffersRepository = TicketsOffersRepositoryImpl(api)