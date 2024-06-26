package com.github.mukiva.feature.airtickets.ui.country_selected_fragment

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.github.mukiva.feature.airtickets.databinding.LaySelectedBarBinding
import com.github.mukiva.feature.airtickets.presentation.SearchState
import com.github.mukiva.feature.airtickets.presentation.SelectedCountryViewModel
import com.github.mukiva.ticketfound.uikit.Component
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ActionBarComponent(
    private val binding: LaySelectedBarBinding,
    private val onBackButton: () -> Unit,
    private val onSwapButton: () -> Unit,
    private val onClearButton: () -> Unit,
) : Component(), Component.IStateObserverComponent<SelectedCountryViewModel> {

    override fun initComponent() = with(binding) {
        initButtons()
    }

    private fun initButtons() = with(binding) {
        swapButton.setOnClickListener {
            onSwapButton()
        }
        navigateBackButton.setOnClickListener {
            onBackButton()
        }
        clearButton.setOnClickListener {
            onClearButton()
        }
    }

    override fun subscribeOnViewModel(
        viewModel: SelectedCountryViewModel,
        owner: LifecycleOwner
    ) {
        viewModel.selectedCountryState
            .flowWithLifecycle(owner.lifecycle)
            .onEach(::updateCountryName)
            .launchIn(owner.lifecycleScope)
    }

    private fun updateCountryName(state: SearchState) {
        updateToCountryName(state.to)
        updateFromCountryName(state.from)
    }

    private fun updateFromCountryName(name: String) = with(binding) {
        fromSearchField.text = name
    }

    private fun updateToCountryName(name: String) = with(binding) {
        toSearchField.text = name
    }
}