package com.github.mukiva.ticketfound.navigation.router

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.github.mukiva.ticketfound.navigation.domain.IRouter
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable

internal const val ARGS_KEY = "ARGS_KEY"

internal class DefaultRouterImpl @AssistedInject constructor(
    @Assisted @IdRes private val fragmentContainerId: Int,
    private val navigationResourceProvider: INavigationResourceProvider,
    private val activity: FragmentActivity
) : IRouter {

    interface Factory {
        fun create(
            @IdRes fragmentContainerId: Int
        ): DefaultRouterImpl
    }

    private val mFragmentCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {

        override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
            super.onFragmentStarted(fm, f)

            if (activity !is AppCompatActivity) return

            val currentNavController = f.findNavController()
            val appBarConfiguration = AppBarConfiguration(currentNavController.graph)

            if (activity.supportActionBar == null) return

            NavigationUI.setupActionBarWithNavController(activity, currentNavController, appBarConfiguration)
        }

    }

    private val mActivityLifecycleObserver = object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            setupNavigationGraph()
            setupActionBarConfiguration()
        }

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            activity.lifecycle.removeObserver(this)
        }
    }

    init {
        activity.lifecycle.addObserver(mActivityLifecycleObserver)

        if (activity.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
            mActivityLifecycleObserver.onCreate(activity)
    }

    override fun launch(destination: Int, args: Serializable) {
        requireNavController().apply {
            navigate(
                resId = destination,
                args = bundleOf(ARGS_KEY to args),
                navOptions = navOptions {
                    anim {
                        exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                    }
                }
            )
        }
    }

    override fun navigateUp(): Boolean {
        requireNavController().popBackStack()
        return true
    }

    private fun setupActionBarConfiguration() {
        if (activity !is AppCompatActivity) return
        activity.supportFragmentManager.apply {
            registerFragmentLifecycleCallbacks(mFragmentCallbacks, true)
        }
    }

    private fun setupNavigationGraph() {
        requireNavController().setGraph(navigationResourceProvider.provideNavigationGraph())
    }

    private fun requireNavController(): NavController {
        val fragmentManager = activity.supportFragmentManager
        val navHost = fragmentManager.findFragmentById(fragmentContainerId) as NavHostFragment
        return navHost.navController
    }
}