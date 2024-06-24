package com.github.mukiva.ticketfound.navigation.router

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.github.mukiva.ticketfound.navigation.domain.IRouter
import com.github.mukiva.ticketfound.navigation.domain.IRouterHolder
import kotlinx.serialization.Serializable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalRouter @Inject constructor() : IRouter {

    private var mActivity: FragmentActivity? = null

    private val mActivityLifecycleObserver = object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            mActivity?.lifecycle?.removeObserver(this)
            mActivity = null
        }
    }

    fun FragmentActivity.setupGlobalRouter() {
        lifecycle.addObserver(mActivityLifecycleObserver)
    }

    override fun launch(destination: Int, args: Serializable) {
        val routerHolder = mActivity as? IRouterHolder ?: return
        val router = routerHolder.requireRouter()
        router.launch(destination, args)
    }

    override fun navigateUp(): Boolean {
        val routerHolder = mActivity as? IRouterHolder ?: return false
        val router = routerHolder.requireRouter()
        return router.navigateUp()
    }
}