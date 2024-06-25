package com.github.mukiva.feature.airtickets.ui.search_bottom_sheet

import androidx.navigation.NavController
import com.github.mukiva.feature.airtickets.R
import com.github.mukiva.feature.airtickets.databinding.LayHintsBinding
import com.github.mukiva.ticketfound.uikit.Component

internal class HintsComponent(
    private val binding: LayHintsBinding,
    private val navController: NavController
) : Component() {

    override fun initComponent() = with(binding) {
        hardRoute.setOnClickListener {
            navController.navigate(R.id.hardRouteFragment)
        }
        anywhere.setOnClickListener {
            navController.navigate(R.id.anywhereFragment)
        }
        weekends.setOnClickListener {
            navController.navigate(R.id.weekendsFragment)
        }
        hotTickets.setOnClickListener {
            navController.navigate(R.id.hotTicketsFragment)
        }
    }
}