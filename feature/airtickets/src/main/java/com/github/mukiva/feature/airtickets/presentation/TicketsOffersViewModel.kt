package com.github.mukiva.feature.airtickets.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mukiva.feature.airtickets.domain.TicketOffers
import com.github.mukiva.feature.airtickets.domain.usecase.GetTicketsOffersUseCase
import com.github.mukiva.ticketfound.data.utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class TicketsOffersViewModel @Inject constructor(
    private val getTicketsOffersUseCase: GetTicketsOffersUseCase
) : ViewModel() {

    val ticketListState: StateFlow<IListState<TicketOffers>> =
        getTicketsOffersUseCase()
            .map(::requestAsState)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = IListState.Loading()
            )

    private fun requestAsState(
        requestResult: RequestResult<List<TicketOffers>>
    ): IListState<TicketOffers> {
        return when (requestResult) {
            is RequestResult.Error -> IListState.Error()
            is RequestResult.InProgress -> IListState.Loading()
            is RequestResult.Success -> IListState.Content(requestResult.data)
        }
    }
}