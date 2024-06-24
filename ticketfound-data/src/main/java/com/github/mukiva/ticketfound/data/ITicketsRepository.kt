package com.github.mukiva.ticketfound.data

import com.github.mukiva.ticketfound.api.ITicketFoundApi
import com.github.mukiva.ticketfound.data.models.Ticket
import com.github.mukiva.ticketfound.data.utils.RequestResult
import com.github.mukiva.ticketfound.data.utils.asDataTickets
import com.github.mukiva.ticketfound.data.utils.asRequestResult
import com.github.mukiva.ticketfound.data.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

interface ITicketsRepository {
    fun getTickets(): Flow<RequestResult<List<Ticket>>>
}

internal class TicketsRepositoryImpl(
    private val ticketFoundMockApi: ITicketFoundApi
) : ITicketsRepository {
    override fun getTickets(): Flow<RequestResult<List<Ticket>>> {
        return getRemoteTickets()
        // TODO Implement room cache
    }

    private fun getRemoteTickets(): Flow<RequestResult<List<Ticket>>> {
        val start = flowOf(RequestResult.InProgress<List<Ticket>>())
        val request = flowOf(ticketFoundMockApi.getTickets())
            .map { result -> result.asRequestResult() }
            .map { requestResult -> requestResult.map(::asDataTickets) }
            .map { requestResult -> requestResult.map { tickets -> tickets.data } }
        return merge(start, request)
    }

}

fun createTicketsRepository(
    api: ITicketFoundApi
): ITicketsRepository = TicketsRepositoryImpl(api)
