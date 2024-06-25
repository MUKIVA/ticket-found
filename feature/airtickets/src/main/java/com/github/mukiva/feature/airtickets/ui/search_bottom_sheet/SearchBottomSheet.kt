package com.github.mukiva.feature.airtickets.ui.search_bottom_sheet

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.mukiva.feature.airtickets.R
import com.github.mukiva.feature.airtickets.databinding.FragmentSearchBinding
import com.github.mukiva.feature.airtickets.presentation.AirTicketsViewModel
import com.github.mukiva.ticketfound.uikit.component
import com.github.mukiva.ticketfound.uikit.viewBindings
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.github.mukiva.ticketfound.uikit.R as UiKitRes
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class SearchBottomSheet : BottomSheetDialogFragment(R.layout.fragment_search) {

    private val mBinding by viewBindings(FragmentSearchBinding::bind)
    private val mViewModel by viewModels<AirTicketsViewModel>(
        ownerProducer = { requireParentFragment() }
    )

    private val mSearchComponent by component {
        SearchBarComponent(
            binding = mBinding.laySearch,
            onToFieldUpdate = mViewModel::updateToSearch,
            onSearch = {  },
            onClear = mViewModel::clearToSearch
        )
    }

    private val mHintsComponent by component {
        HintsComponent(
            binding = mBinding.layHints,
            navController = findNavController()
        )
    }

    private val mRecommendationsComponent by component {
        RecommendationsComponent(
            binding = mBinding.layRecommendation,
            onRecommendationClick = mViewModel::updateToSearch
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        return BottomSheetDialog(requireContext(), UiKitRes.style.TicketFound_BottomSheet)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mHintsComponent.initComponent()
        mRecommendationsComponent.initComponent()
        mSearchComponent.initComponent()
        mSearchComponent.subscribeOnViewModel(mViewModel, viewLifecycleOwner)

    }
}