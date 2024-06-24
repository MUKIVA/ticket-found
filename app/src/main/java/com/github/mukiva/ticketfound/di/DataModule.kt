package com.github.mukiva.ticketfound.di

import android.content.Context
import com.github.mukiva.ticketfound.BuildConfig
import com.github.mukiva.ticketfound.api.ITicketFoundApi
import com.github.mukiva.ticketfound.api.createMockTicketFoundApi
import com.github.mukiva.ticketfound.api.createRetrofit
import com.github.mukiva.ticketfound.data.createOffersRepository
import com.github.mukiva.ticketfound.data.createTicketsOffersRepository
import com.github.mukiva.ticketfound.data.createTicketsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() = createOkHttp()

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        json: Json,
    ): Retrofit = createRetrofit(
        baseUrl = BuildConfig.API_BASE_URL,
        okHttpClient = client,
        json = json,
    )

    @Provides
    @Singleton
    fun provideTicketFoundApi(
        @ApplicationContext context: Context,
        json: Json
    ): ITicketFoundApi = createMockTicketFoundApi(context, json)

    @Provides
    @Singleton
    fun provideOffersRepository(
        api: ITicketFoundApi
    ) = createOffersRepository(api)

    @Provides
    @Singleton
    fun provideTicketsOffersRepository(
        api: ITicketFoundApi
    ) = createTicketsOffersRepository(api)

    @Provides
    @Singleton
    fun provideTicketsRepository(
        api: ITicketFoundApi
    ) = createTicketsRepository(api)

}