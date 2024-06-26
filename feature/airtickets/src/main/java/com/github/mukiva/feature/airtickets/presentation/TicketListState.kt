package com.github.mukiva.feature.airtickets.presentation

import com.github.mukiva.feature.airtickets.domain.Ticket

sealed interface ITicketListState {
    data object Loading : ITicketListState
    data object Error : ITicketListState
    data class Content(
        val data: List<Ticket>
    ) : ITicketListState
}