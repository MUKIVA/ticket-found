package com.github.mukiva.ticketfound.navigation.domain

import androidx.annotation.IdRes
import kotlinx.serialization.Serializable

interface IRouter {
    fun launch(@IdRes destination: Int, args: Serializable)
    fun navigateUp(): Boolean
}