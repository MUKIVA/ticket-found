package com.github.mukiva.feature.airtickets.ui.country_selected_fragment

import android.content.res.ColorStateList
import android.graphics.Rect
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.github.mukiva.feature.airtickets.R
import com.github.mukiva.feature.airtickets.databinding.ItemTicketBinding
import com.github.mukiva.feature.airtickets.databinding.LayTicketRecommendationBinding
import com.github.mukiva.feature.airtickets.domain.Ticket
import com.github.mukiva.feature.airtickets.presentation.IListState
import com.github.mukiva.feature.airtickets.presentation.SelectedCountryViewModel
import com.github.mukiva.feature.airtickets.ui.AirTicketsAdapterDelegates
import com.github.mukiva.ticketfound.uikit.Component
import com.github.mukiva.ticketfound.uikit.error
import com.github.mukiva.ticketfound.uikit.hide
import com.github.mukiva.ticketfound.uikit.loading
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.github.mukiva.ticketfound.uikit.R as UiKitRes

class TicketListComponent(
    private val binding: LayTicketRecommendationBinding
) : Component(), Component.IStateObserverComponent<SelectedCountryViewModel> {

    private val mTicketListAdapter = ListDelegationAdapter(
        AirTicketsAdapterDelegates.ticketAdapterDelegate
    )

    private val mItemDecoration = object : RecyclerView.ItemDecoration() {

        private val mColorRed by lazy {
            binding.root.resources.getColor(UiKitRes.color.red, binding.root.context.theme)
        }

        private val mColorBlue by lazy {
            binding.root.resources.getColor(UiKitRes.color.blue, binding.root.context.theme)
        }

        private val mColorWhite by lazy {
            binding.root.resources.getColor(UiKitRes.color.white, binding.root.context.theme)
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

            val binding = ItemTicketBinding.bind(view)

            when (itemPosition) {
                0 -> {
                    binding.mark.backgroundTintList =
                        ColorStateList.valueOf(mColorRed)
                }
                1 -> {
                    binding.mark.backgroundTintList =
                        ColorStateList.valueOf(mColorBlue)
                }
                else -> {
                    binding.mark.backgroundTintList =
                        ColorStateList.valueOf(mColorWhite)
                }
            }
        }
    }

    override fun initComponent() = with(binding) {
        ticketList.adapter = mTicketListAdapter
        ticketList.addItemDecoration(mItemDecoration)
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

    private fun updateListState(state: IListState<Ticket>) {
        when (state) {
            is IListState.Content -> setContentState(state.data)
            is IListState.Error -> setErrorState()
            is IListState.Loading -> setLoadingState()
        }
    }

    private fun setContentState(data: List<Ticket>) = with(binding) {
        ticketListEmptyView.hide()
        ticketList.isVisible = true
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