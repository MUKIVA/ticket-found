package com.github.mukiva.feature.airtickets.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mukiva.feature.airtickets.domain.Ticket
import com.github.mukiva.feature.airtickets.domain.usecase.GetTicketListUseCase
import com.github.mukiva.ticketfound.data.utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SelectedCountryViewModel @Inject constructor(
    private val getTicketListUseCase: GetTicketListUseCase
) : ViewModel() {

    val selectedCountryState: StateFlow<SearchState>
        get() = mSelectedCountryStateFlow.asStateFlow()

    val configurationState: StateFlow<ConfigurationState>
        get() = mConfigurationStateFlow.asStateFlow()

    val ticketListState: StateFlow<ITicketListState>
        get() = getTicketListUseCase()
            .map(::requestAsTicketListState)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = ITicketListState.Loading
            )

    private val mSelectedCountryStateFlow = MutableStateFlow(SearchState.default())
    private val mConfigurationStateFlow = MutableStateFlow(ConfigurationState.default())

    fun updateFromCountryName(name: String) {
        mSelectedCountryStateFlow.update { state ->
            state.copy(
                from = name
            )
        }
    }

    fun updateToCountryName(name: String) {
        mSelectedCountryStateFlow.update { state ->
            state.copy(
                to = name
            )
        }
    }

    fun updateStartDate(year: Int, month: Int, dayOfWeek: Int) {
        mConfigurationStateFlow.update { state ->
            state.copy(
                startDate = LocalDate.of(year, month, dayOfWeek).atStartOfDay()
            )
        }
    }

    fun updateEndDate(year: Int, month: Int, dayOfWeek: Int) {
        mConfigurationStateFlow.update { state ->
            state.copy(
                endDate = LocalDate.of(year, month, dayOfWeek).atStartOfDay()
            )
        }
    }

    fun swapCountry() {
        mSelectedCountryStateFlow.update { state ->
            val tmp = state.from
            state.copy(
                from = state.to,
                to = tmp
            )
        }
    }

    private fun requestAsTicketListState(
        requestResult: RequestResult<List<Ticket>>
    ): ITicketListState {
        return when (requestResult) {
            is RequestResult.Error -> ITicketListState.Error
            is RequestResult.InProgress -> ITicketListState.Loading
            is RequestResult.Success -> ITicketListState.Content(requestResult.data)
        }
    }
}