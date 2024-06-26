package com.github.mukiva.feature.airtickets.ui.air_tickets_fragment

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.github.mukiva.feature.airtickets.databinding.LaySearchCardBinding
import com.github.mukiva.feature.airtickets.presentation.AirTicketsViewModel
import com.github.mukiva.feature.airtickets.presentation.SearchState
import com.github.mukiva.ticketfound.uikit.Component
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

internal class AirTicketsSearchComponent(
    private val binding: LaySearchCardBinding,
    private val onFromFieldUpdate: (String) -> Unit,
    private val onSearch: () -> Unit
) : Component(), Component.IStateObserverComponent<AirTicketsViewModel> {

    override fun initComponent() {
        binding.fromSearchField
            .doOnTextChanged { text, _, _, _ ->
                onFromFieldUpdate(text.toString())
            }
        binding.toSearchField
            .onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                onSearch()
            }
        }
    }

    override fun subscribeOnViewModel(
        viewModel: AirTicketsViewModel,
        owner: LifecycleOwner,
    ) {
        viewModel.searchStateFlow
            .flowWithLifecycle(owner.lifecycle)
            .map { link -> link.value }
            .onEach(::onStateUpdate)
            .launchIn(owner.lifecycleScope)
    }

    private fun onStateUpdate(state: SearchState) {
        updateFromField(state.from)
        updateToField(state.to)
    }

    private fun updateFromField(text: String) = with(binding) {
        if (text != fromSearchField.text.toString()) {
            fromSearchField.setText(text)
        }
    }

    private fun updateToField(text: String) = with(binding) {
        if (text != toSearchField.text.toString()) {
            toSearchField.setText(text)
        }
    }
}