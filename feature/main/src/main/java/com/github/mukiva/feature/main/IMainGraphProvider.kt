package com.github.mukiva.feature.main

import androidx.annotation.NavigationRes

interface IMainGraphProvider {

    @NavigationRes
    fun provideMainGraph(): Int
}