package com.github.mukiva.ticketfound.data

import com.github.mukiva.ticketfound.api.ITicketFoundApi
import com.github.mukiva.ticketfound.data.models.Offer
import com.github.mukiva.ticketfound.data.models.Ticket
import com.github.mukiva.ticketfound.data.utils.RequestResult
import com.github.mukiva.ticketfound.data.utils.asDataOffers
import com.github.mukiva.ticketfound.data.utils.asDataTickets
import com.github.mukiva.ticketfound.data.utils.asRequestResult
import com.github.mukiva.ticketfound.data.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

interface IOffersRepository {
    fun getOffers(): Flow<RequestResult<List<Offer>>>
}

internal class OffersRepositoryImpl(
    private val ticketFoundMockApi: ITicketFoundApi
) : IOffersRepository {

    override fun getOffers(): Flow<RequestResult<List<Offer>>> {
        return getRemoteOffers()
        //TODO Implement room cache
    }

    private fun getRemoteOffers(): Flow<RequestResult<List<Offer>>> {
        val start = flowOf(RequestResult.InProgress<List<Offer>>())
        val request = flowOf(ticketFoundMockApi.getOffers())
            .map { result -> result.asRequestResult() }
            .map { requestResult -> requestResult.map(::asDataOffers) }
            .map { requestResult -> requestResult.map { offers -> offers.data } }

        return merge(start, request)
    }
}

fun createOffersRepository(
    api: ITicketFoundApi
) : IOffersRepository = OffersRepositoryImpl(api)