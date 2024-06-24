package com.github.mukiva.ticketfound.di

import com.github.mukiva.feature.main.IMainGraphProvider
import com.github.mukiva.ticketfound.R
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
                return R.id.mainFragment
            }

            override fun provideNavigationGraph(): Int {
                return R.navigation.navigation_application
            }

        }
    }

    @Provides
    fun provideMainGraphProvider(): IMainGraphProvider {
        return object : IMainGraphProvider {
            override fun provideMainGraph(): Int {
                return R.navigation.main_graph
            }
        }
    }

}