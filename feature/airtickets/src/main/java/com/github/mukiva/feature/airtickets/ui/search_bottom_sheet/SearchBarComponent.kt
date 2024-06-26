package com.github.mukiva.feature.airtickets.ui.search_bottom_sheet

import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.github.mukiva.feature.airtickets.databinding.LaySearchFieldBinding
import com.github.mukiva.feature.airtickets.presentation.AirTicketsViewModel
import com.github.mukiva.feature.airtickets.presentation.LinkWrapper
import com.github.mukiva.feature.airtickets.presentation.SearchState
import com.github.mukiva.ticketfound.uikit.Component
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class SearchBarComponent(
    private val binding: LaySearchFieldBinding,
    private val onToFieldUpdate: (String) -> Unit,
    private val onClear: () -> Unit,
    private val onSearch: () -> Unit
) : Component(), Component.IStateObserverComponent<AirTicketsViewModel> {

    override fun initComponent() = with(binding) {
        toSearchField
            .doOnTextChanged { text, _, _, _ ->
                onToFieldUpdate(text.toString())
            }
        toSearchField
            .setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        onSearch()
                        true
                    }
                    else -> false
                }
            }
        clearButton.setOnClickListener {
            onClear()
        }
    }

    override fun subscribeOnViewModel(
        viewModel: AirTicketsViewModel,
        owner: LifecycleOwner
    ) {
        viewModel.searchStateFlow
            .flowWithLifecycle(owner.lifecycle)
            .onEach(::updateSearchField)
            .launchIn(owner.lifecycleScope)
    }

    private fun updateSearchField(state: LinkWrapper<SearchState>) {
        updateFromField(state.value.from)
        updateToField(state.value.to)
    }

    private fun updateFromField(text: String) {
        binding.fromField.text = text
    }

    private fun updateToField(text: String) = with(binding) {
        if (toSearchField.text.toString() != text) {
            toSearchField.setText(text)
        }
    }
}