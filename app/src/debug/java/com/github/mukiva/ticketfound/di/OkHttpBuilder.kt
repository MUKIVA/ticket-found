package com.github.mukiva.ticketfound.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun createOkHttp() = OkHttpClient.Builder()
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    )
    .build()