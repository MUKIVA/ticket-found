package com.github.mukiva.feature.airtickets.ui.air_tickets_fragment

import android.graphics.Rect
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.github.mukiva.feature.airtickets.R
import com.github.mukiva.feature.airtickets.databinding.LayOfferListBinding
import com.github.mukiva.feature.airtickets.domain.Offer
import com.github.mukiva.feature.airtickets.presentation.AirTicketsViewModel
import com.github.mukiva.feature.airtickets.presentation.IOffersState
import com.github.mukiva.feature.airtickets.ui.AirTicketsAdapterDelegates
import com.github.mukiva.ticketfound.uikit.Component
import com.github.mukiva.ticketfound.uikit.error
import com.github.mukiva.ticketfound.uikit.hide
import com.github.mukiva.ticketfound.uikit.loading
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class AirTicketsOfferListComponent(
    private val binding: LayOfferListBinding
) : Component(), Component.IStateObserverComponent<AirTicketsViewModel> {

    private val mAdapter = ListDelegationAdapter(
        AirTicketsAdapterDelegates.offerAdapterDelegate
    )
    private val mItemDecoration = object : RecyclerView.ItemDecoration() {

        private val mPadding by lazy {
            binding.root.context.resources.getDimension(R.dimen.list_item_gap).toInt()
        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            val itemPosition = parent.getChildAdapterPosition(view)

            if (itemPosition == RecyclerView.NO_POSITION) {
                return
            }

            if (itemPosition != 0) {
                outRect.set(mPadding, view.paddingTop, view.paddingRight, view.paddingBottom)
            }
        }
    }

    override fun initComponent() = with(binding) {
        list.adapter = mAdapter
        list.addItemDecoration(mItemDecoration)
    }

    override fun subscribeOnViewModel(
        viewModel: AirTicketsViewModel,
        owner: LifecycleOwner
    ) {
         viewModel.offersStateFlow
            .flowWithLifecycle(owner.lifecycle)
            .onEach(::onStateUpdate)
            .launchIn(owner.lifecycleScope)
    }

    private fun onStateUpdate(state: IOffersState) {
        when (state) {
            is IOffersState.Content -> setContentState(state.offers)
            IOffersState.Error -> setErrorState()
            IOffersState.Loading -> setLoadingState()
        }
    }

    private fun setErrorState() = with(binding) {
        list.isVisible = false
        emptyView.error()
    }

    private fun setLoadingState() = with(binding) {
        list.isVisible = false
        emptyView.loading()
    }

    private fun setContentState(items: List<Offer>) = with(binding) {
        list.isVisible = true
        emptyView.hide()
        mAdapter.items = items
    }
}