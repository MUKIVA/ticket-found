package com.github.mukiva.feature.airtickets.ui.air_tickets_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mukiva.feature.airtickets.R
import com.github.mukiva.feature.airtickets.databinding.FragmentAirTicketsBinding
import com.github.mukiva.feature.airtickets.presentation.AirTicketsViewModel
import com.github.mukiva.feature.airtickets.ui.search_bottom_sheet.SearchBottomSheet
import com.github.mukiva.ticketfound.uikit.component
import com.github.mukiva.ticketfound.uikit.viewBindings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class AirTicketsFragment : Fragment(R.layout.fragment_air_tickets) {

    private val mBinding by viewBindings(FragmentAirTicketsBinding::bind)
    private val mViewModel by viewModels<AirTicketsViewModel>()

    private val mSearchComponent by component {
        AirTicketsSearchComponent(
            binding = mBinding.search,
            onFromFieldUpdate = mViewModel::updateFromSearch,
            onSearch = { showSearchBottomSheet() }
        )
    }

    private val mOfferListComponent by component {
        AirTicketsOfferListComponent(
            binding = mBinding.offerList
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSearchComponent.initComponent()
        mSearchComponent.subscribeOnViewModel(mViewModel, viewLifecycleOwner)

        mOfferListComponent.initComponent()
        mOfferListComponent.subscribeOnViewModel(mViewModel, viewLifecycleOwner)
    }

    override fun onStop() {
        super.onStop()
        mViewModel.rememberLastFromLocation()
    }

    private fun showSearchBottomSheet() {
        val bottomSheet = SearchBottomSheet()
        bottomSheet.show(childFragmentManager, SEARCH_BOTTOM_SHEET_TAG)
    }

    companion object {
        private const val SEARCH_BOTTOM_SHEET_TAG = "SEARCH_BOTTOM_SHEET_TAG"
    }
}