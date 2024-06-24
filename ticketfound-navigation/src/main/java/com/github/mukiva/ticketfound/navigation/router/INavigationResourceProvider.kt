package com.github.mukiva.ticketfound.navigation.router

import androidx.annotation.IdRes
import androidx.annotation.NavigationRes

interface INavigationResourceProvider {

    @IdRes
    fun provideStartDestination(): Int

    @NavigationRes
    fun provideNavigationGraph(): Int

}