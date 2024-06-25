package com.github.mukiva.ticketfound.uikit

import androidx.core.view.isVisible
import com.github.mukiva.ticketfound.uikit.databinding.LayListStatesBinding

fun LayListStatesBinding.error() {
    root.isVisible = true
    message.isVisible = true
    progressIndicator.isVisible = false
    message.text = root.context.getString(R.string.list_state_error)
}

fun LayListStatesBinding.loading() {
    root.isVisible = true
    message.isVisible = false
    progressIndicator.isVisible = true
}

fun LayListStatesBinding.empty() {
    root.isVisible = true
    message.isVisible = true
    progressIndicator.isVisible = false
    message.text = root.context.getString(R.string.list_state_empty)
}

fun LayListStatesBinding.hide() {
    root.isVisible = false
}