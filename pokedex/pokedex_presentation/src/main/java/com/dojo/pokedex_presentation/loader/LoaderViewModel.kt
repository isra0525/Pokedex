package com.dojo.pokedex_presentation.loader

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.core.util.UiEvent
import com.dojo.pokedex_domain.use_case.PokedexUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoaderViewModel @Inject constructor(
    private val pokedexUseCases: PokedexUseCases
): ViewModel() {
    var state by mutableStateOf(LoaderState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getAndStoreJob: Job? = null
/*    init {
        onEvent(LoaderEvent.OnLoadPokemons(151))
    }*/

    fun onEvent(event: LoaderEvent) {
        when(event) {
            is LoaderEvent.OnLoadPokemons -> {
                executeFetchPokemons(event.quantity)
            }
            is LoaderEvent.OnLoadFinishedRedirect -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Success)
                }
            }
        }
    }

    private fun executeFetchPokemons(quantity : Int) {
        getAndStoreJob = pokedexUseCases.storePokemons(quantity)
            .onEach { pokemonProgress ->
                state = state.copy(
                    progress = pokemonProgress.progress,
                    pokemonName = pokemonProgress.pokemonName
                )
                println("El estado es")
                println(state)
            }.launchIn(CoroutineScope(Dispatchers.IO))
        getAndStoreJob?.invokeOnCompletion {
            onEvent(LoaderEvent.OnLoadFinishedRedirect)
        }
    }
}