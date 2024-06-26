package com.github.mukiva.feature.airtickets.ui.all_tickets_fragment

import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.github.mukiva.feature.airtickets.databinding.LayAllTicketListBinding
import com.github.mukiva.feature.airtickets.domain.TicketOffers
import com.github.mukiva.feature.airtickets.presentation.IListState
import com.github.mukiva.feature.airtickets.presentation.TicketsOffersViewModel
import com.github.mukiva.feature.airtickets.ui.AirTicketsAdapterDelegates
import com.github.mukiva.ticketfound.uikit.Component
import com.github.mukiva.ticketfound.uikit.error
import com.github.mukiva.ticketfound.uikit.hide
import com.github.mukiva.ticketfound.uikit.loading
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class TicketListComponent(
    private val binding: LayAllTicketListBinding
) : Component(), Component.IStateObserverComponent<TicketsOffersViewModel> {

    private val mAdapter = ListDelegationAdapter(
         AirTicketsAdapterDelegates.ticketOffersAdapterDelegate
    )

    override fun initComponent() = with(binding) {
        list.adapter = mAdapter
    }

    override fun subscribeOnViewModel(
        viewModel: TicketsOffersViewModel,
        owner: LifecycleOwner
    ) {
        viewModel.ticketListState
            .flowWithLifecycle(owner.lifecycle)
            .onEach(::updateListState)
            .launchIn(owner.lifecycleScope)
    }

    private fun updateListState(state: IListState<TicketOffers>) {
        when (state) {
            is IListState.Content -> setContentState(state.data)
            is IListState.Error -> setErrorState()
            is IListState.Loading -> setLoadingState()
        }
    }

    private fun setErrorState() = with(binding) {
        emptyView.error()
        list.isVisible = false
    }

    private fun setLoadingState() = with(binding) {
        emptyView.loading()
        list.isVisible = false
    }

    private fun setContentState(items: List<TicketOffers>) = with(binding) {
        emptyView.hide()
        list.isVisible = true
        mAdapter.items = items
    }
}