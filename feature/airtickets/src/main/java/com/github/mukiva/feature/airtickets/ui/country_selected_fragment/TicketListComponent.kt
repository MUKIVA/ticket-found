package com.github.mukiva.feature.airtickets.ui.country_selected_fragment

import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.github.mukiva.feature.airtickets.databinding.LayTicketRecommendationBinding
import com.github.mukiva.feature.airtickets.domain.Ticket
import com.github.mukiva.feature.airtickets.presentation.ITicketListState
import com.github.mukiva.feature.airtickets.presentation.SelectedCountryViewModel
import com.github.mukiva.feature.airtickets.ui.AirTicketsAdapterDelegates
import com.github.mukiva.ticketfound.uikit.Component
import com.github.mukiva.ticketfound.uikit.error
import com.github.mukiva.ticketfound.uikit.hide
import com.github.mukiva.ticketfound.uikit.loading
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TicketListComponent(
    private val binding: LayTicketRecommendationBinding
) : Component(), Component.IStateObserverComponent<SelectedCountryViewModel> {

    private val mTicketListAdapter = ListDelegationAdapter(
        AirTicketsAdapterDelegates.ticketAdapterDelegate
    )

    override fun initComponent() = with(binding) {
        ticketList.adapter = mTicketListAdapter
    }

    override fun subscribeOnViewModel(
        viewModel: SelectedCountryViewModel,
        owner: LifecycleOwner
    ) {
        viewModel.ticketListState
            .flowWithLifecycle(owner.lifecycle)
            .onEach(::updateListState)
            .launchIn(owner.lifecycleScope)
    }

    private fun updateListState(state: ITicketListState) = with(binding) {
        when (state) {
            is ITicketListState.Content -> setContentState(state.data)
            ITicketListState.Error -> setErrorState()
            ITicketListState.Loading -> setLoadingState()
        }
    }

    private fun setContentState(data: List<Ticket>) = with(binding) {
        ticketListEmptyView.hide()
        mTicketListAdapter.items = data
    }

    private fun setErrorState() = with(binding) {
        ticketList.isVisible = false
        ticketListEmptyView.error()
    }

    private fun setLoadingState() = with(binding) {
        ticketList.isVisible = false
        ticketListEmptyView.loading()
    }
}