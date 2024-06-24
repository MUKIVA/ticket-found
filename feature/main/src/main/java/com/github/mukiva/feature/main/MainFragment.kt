package com.github.mukiva.feature.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.github.mukiva.feature.main.databinding.FragmentMainBinding
import com.github.mukiva.ticketfound.uikit.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
internal class MainFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var graphProvider: IMainGraphProvider

    private val mBinding by viewBindings(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragmentViewContainer()
    }

    private fun setupFragmentViewContainer() = with(mBinding) {
        val navHost = childFragmentManager
            .findFragmentById(R.id.mainContainer) as NavHostFragment
        navHost.navController.setGraph(graphProvider.provideMainGraph())
        bottomNavigation.setupWithNavController(navHost.navController)
    }

}