package com.github.mukiva.feature.airtickets.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mukiva.feature.airtickets.domain.Offer
import com.github.mukiva.feature.airtickets.domain.usecase.GetOffersUseCase
import com.github.mukiva.ticketfound.data.utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AirTicketsViewModel @Inject constructor(
    private val getOffersUseCase: GetOffersUseCase
) : ViewModel() {

    val searchStateFlow: StateFlow<LinkWrapper<SearchState>>
        get() = mSearchStateFlow

    val offersStateFlow: StateFlow<IOffersState>
        get() = getOffersUseCase()
            .map(::requestAsOffersState)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = IOffersState.Loading
            )
    private val mSearchStateFlow =
        MutableStateFlow(LinkWrapper(SearchState.default()))

    fun updateFromSearch(text: String) {
        mSearchStateFlow.update { state ->
            LinkWrapper(state.value.copy(
                from = cyrillicFilter(text)
            ))
        }
    }

    fun updateToSearch(text: String) {
        mSearchStateFlow.update { state ->
            LinkWrapper(state.value.copy(
                to = cyrillicFilter(text)
            ))
        }
    }

    fun clearToSearch() {
        mSearchStateFlow.update { state ->
            LinkWrapper(state.value.copy(
                to = ""
            ))
        }
    }

    private fun cyrillicFilter(text: String): String {
        Log.d("SEARCH", text)
        return text.filter { ch ->
            ch in mLowerCaseCyrillicRange || ch in mUpperCaseCyrillicRange || ch == 'ё' || ch == 'Ё'
        }
    }

    private fun requestAsOffersState(
        requestResult: RequestResult<List<Offer>>
    ): IOffersState = when (requestResult) {
        is RequestResult.Error -> IOffersState.Error
        is RequestResult.InProgress -> IOffersState.Loading
        is RequestResult.Success -> IOffersState.Content(requestResult.data)
    }

    companion object {
        private val mLowerCaseCyrillicRange = 'а'..'я'
        private val mUpperCaseCyrillicRange = 'А'..'Я'
    }
}