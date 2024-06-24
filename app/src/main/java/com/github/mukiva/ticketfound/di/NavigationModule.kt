package com.github.mukiva.ticketfound.di

import com.github.mukiva.ticketfound.navigation.router.INavigationResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Provides
    fun provideNavigationResourceProvider(): INavigationResourceProvider {
        return object : INavigationResourceProvider {

            override fun provideStartDestination(): Int {
                TODO("Not yet implemented")
            }

            override fun provideNavigationGraph(): Int {
                TODO("Not yet implemented")
            }

        }
    }

}