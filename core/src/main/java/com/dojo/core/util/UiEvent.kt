package com.dojo.core.util

sealed class UiEvent {
    object Success: UiEvent()
    object NavigateUp: UiEvent()
}