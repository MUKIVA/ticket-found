package com.github.mukiva.ticketfound.api

import android.content.Context
import com.github.mukiva.ticketfound.api.models.OffersDto
import com.github.mukiva.ticketfound.api.models.TicketsDto
import com.github.mukiva.ticketfound.api.models.TicketsOffers
import kotlinx.serialization.json.Json

class TicketFoundMockApi internal constructor(
    private val context: Context,
    private val json: Json
) : ITicketFoundApi {

    override fun getOffers(): Result<OffersDto> = wrapTry {
        val content = readString("offers.json")
        json.decodeFromString<OffersDto>(content)
    }

    override fun getTicketsOffers(): Result<TicketsOffers> = wrapTry {
        val content = readString("tickets_offers.json")
        json.decodeFromString<TicketsOffers>(content)
    }

    override fun getTickets(): Result<TicketsDto> = wrapTry {
        val content = readString("tickets.json")
        json.decodeFromString<TicketsDto>(content)
    }

    private fun <T> wrapTry(block: () -> T): Result<T> {
        return try {
            val obj = block()
            Result.success<T>(obj)
        } catch (cause: Exception) {
            Result.failure<T>(cause)
        }
    }

    private fun readString(fileName: String): String {
        val content = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
        return content
    }
}

fun createMockTicketFoundApi(
    context: Context,
    json: Json
) = TicketFoundMockApi(context, json)