package com.github.mukiva.feature.airtickets.presentation

import com.github.mukiva.feature.airtickets.domain.Offer

sealed interface IOffersState {
    data object Loading : IOffersState
    data object Error : IOffersState
    data class Content(
        val offers: List<Offer>
    ) : IOffersState
}