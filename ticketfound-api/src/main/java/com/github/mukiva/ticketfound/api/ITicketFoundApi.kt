package com.github.mukiva.ticketfound.api

import com.github.mukiva.ticketfound.api.models.OffersDto
import com.github.mukiva.ticketfound.api.models.TicketsDto
import com.github.mukiva.ticketfound.api.models.TicketsOffersDto
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET

interface ITicketFoundApi {
    @GET("/offers")
    fun getOffers(): Result<OffersDto>
    @GET("/tickets_offers")
    fun getTicketsOffers(): Result<TicketsOffersDto>
    @GET("/tickets")
    fun getTickets(): Result<TicketsDto>
}

private fun retrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json
): Retrofit {
    val converterFactory = json.asConverterFactory(
        contentType = "application/json; charset=UTF8".toMediaType()
    )

    val modifiedClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
//      TODO(.addInterceptor(ApiKeyInterceptor(apiKey)))
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(modifiedClient)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .addConverterFactory(converterFactory)
        .build()
}

fun createRetrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json
) = retrofit(baseUrl, okHttpClient, json)