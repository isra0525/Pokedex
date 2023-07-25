package com.dojo.pokedex_presentation.welcome

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dojo.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class WelcomeViewLoader @Inject constructor() : ViewModel() {
    var quantity by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onQuantityEnter(value: String) {
        if (!value.isNullOrBlank()) {
            this.quantity = value
        } else {
            this.quantity = ""
        }
    }


}