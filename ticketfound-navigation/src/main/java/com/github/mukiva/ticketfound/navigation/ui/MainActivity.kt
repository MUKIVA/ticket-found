package com.github.mukiva.ticketfound.navigation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mukiva.ticketfound.navigation.R
import com.github.mukiva.ticketfound.navigation.domain.IRouter
import com.github.mukiva.ticketfound.navigation.domain.IRouterHolder
import com.github.mukiva.ticketfound.navigation.router.DefaultRouterImpl
import com.github.mukiva.ticketfound.navigation.router.GlobalRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class MainActivity : AppCompatActivity(), IRouterHolder {

    @Inject
    lateinit var defaultRouter: DefaultRouterImpl.Factory

    @Inject
    lateinit var globalRouter: GlobalRouter

    private val mRouter by lazy { defaultRouter.create(R.id.mainNavHost) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        with(globalRouter) {
            setupGlobalRouter()
        }
    }

    override fun requireRouter(): IRouter {
        return mRouter
    }

    override fun onSupportNavigateUp(): Boolean {
        return mRouter.navigateUp() || super.onSupportNavigateUp()
    }
}