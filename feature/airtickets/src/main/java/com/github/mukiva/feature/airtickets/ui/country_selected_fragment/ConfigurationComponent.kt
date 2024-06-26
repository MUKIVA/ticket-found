package com.github.mukiva.feature.airtickets.ui.country_selected_fragment

import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.github.mukiva.feature.airtickets.R
import com.github.mukiva.feature.airtickets.databinding.LayConfigurationBinding
import com.github.mukiva.feature.airtickets.presentation.ClassType
import com.github.mukiva.feature.airtickets.presentation.ConfigurationState
import com.github.mukiva.feature.airtickets.presentation.SelectedCountryViewModel
import com.github.mukiva.ticketfound.uikit.Component
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import com.github.mukiva.ticketfound.uikit.R as UiKitRes


class ConfigurationComponent(
    private val binding: LayConfigurationBinding,
    private val onEndDateClick: () -> Unit,
    private val onStartDateClick: () -> Unit,
    private val onFilterClick: () -> Unit,
    private val onClassClick: () -> Unit
) : Component(), Component.IStateObserverComponent<SelectedCountryViewModel> {

    private val mDateFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("dd LLL, EEE", Locale.getDefault())

    override fun initComponent() = with(binding) {
        initButtons()
        root.isHorizontalScrollBarEnabled = false
        root.isVerticalScrollBarEnabled = false
    }

    override fun subscribeOnViewModel(
        viewModel: SelectedCountryViewModel,
        owner: LifecycleOwner
    ) {
        viewModel.configurationState
            .flowWithLifecycle(owner.lifecycle)
            .onEach(::updateConfiguration)
            .launchIn(owner.lifecycleScope)
    }

    private fun updateConfiguration(state: ConfigurationState) {
        updateBackDateState(state.endDate)
        updateStartDateState(state.startDate)
        updateClassType(state.personCount, state.classType)
    }

    private fun updateClassType(personCount: Int, classType: ClassType) = with(binding) {
        // TODO: Возможно в будущем
    }

    private fun updateStartDateState(date: LocalDateTime) = with(binding) {
        startConfiguration.text = mDateFormatter.format(date)
    }

    private fun updateBackDateState(date: LocalDateTime?) {
        date?.let { setBackDateState(date) }
    }

    private fun setBackDateState(date: LocalDateTime) = with(binding) {
        backConfiguration.text = mDateFormatter.format(date)
        backConfiguration.setCompoundDrawables(null, null, null, null)
    }

    private fun initButtons() = with(binding) {
        backConfiguration.setOnClickListener {
            onEndDateClick()
        }
        startConfiguration.setOnClickListener {
            onStartDateClick()
        }
        personConfiguration.setOnClickListener {
            onClassClick()
        }
        filterConfiguration.setOnClickListener {
            onFilterClick()
        }
    }

}