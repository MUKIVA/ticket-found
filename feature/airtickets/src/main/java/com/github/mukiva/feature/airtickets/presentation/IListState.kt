package com.github.mukiva.feature.airtickets.presentation

sealed interface IListState<T> {
    class Error<T> : IListState<T>
    class Loading<T> : IListState<T>
    data class Content<T>(val data: List<T>) : IListState<T>
}