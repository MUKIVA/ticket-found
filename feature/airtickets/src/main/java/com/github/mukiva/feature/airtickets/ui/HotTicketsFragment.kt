package com.github.mukiva.feature.airtickets.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.mukiva.feature.airtickets.R
import com.github.mukiva.feature.airtickets.databinding.FragmentHotTicketsBinding
import com.github.mukiva.ticketfound.uikit.viewBindings

class HotTicketsFragment : Fragment(R.layout.fragment_hot_tickets) {

    private val mBinding by viewBindings(FragmentHotTicketsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

}