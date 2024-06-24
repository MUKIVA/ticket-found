package com.github.mukiva.feature.airtickets

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.mukiva.feature.airtickets.databinding.FragmentAirTicketsBinding
import com.github.mukiva.ticketfound.uikit.viewBindings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class AirTicketsFragment : Fragment(R.layout.fragment_air_tickets) {

    private val mBinding by viewBindings(FragmentAirTicketsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}