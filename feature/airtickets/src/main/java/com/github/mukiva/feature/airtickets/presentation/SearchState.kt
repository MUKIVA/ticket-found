package com.github.mukiva.feature.airtickets.presentation

data class SearchState(
    val from: String,
    val to: String
) {
    companion object {
        fun default() = SearchState(
            from = "",
            to = ""
        )
    }
}

class LinkWrapper<T>(val value: T)

