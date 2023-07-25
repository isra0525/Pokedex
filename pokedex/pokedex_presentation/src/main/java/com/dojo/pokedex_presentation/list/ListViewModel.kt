package com.dojo.pokedex_presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.core.util.UiEvent
import com.dojo.pokedex_domain.use_case.PokedexUseCases
import com.dojo.pokedex_presentation.list.components.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCases: PokedexUseCases
): ViewModel() {
    var state by mutableStateOf(ListState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        onEvent(ListEvent.OnLoadPokemons)
    }

    fun onEvent(event: ListEvent) {
        when(event) {
            is ListEvent.OnLoadPokemons -> {
                execGetPokemonsStored()
            }
            is ListEvent.OnClickPokemon -> {
            }
            is ListEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }
            is ListEvent.OnSearch -> executeSearch()
            is ListEvent.OnSearchFocusChange -> {
                state = state.copy(isHintVisible = !event.isFocussed && state.query.isBlank())
            }
        }
    }

    fun execGetPokemonsStored(){
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getStoredPokemons().collect { pokemonList ->
                state = state.copy(
                    pokemons = pokemonList
                )
            }
        }
    }

    private fun executeSearch() {
        viewModelScope.launch {
            state = state.copy(
                isSearching = true,
                pokemons = emptyList()
            )
            useCases.searchPokemonByName(state.query)
                .onSuccess { pokemons ->
                    state = state.copy(
                        pokemons = pokemons,
                        isSearching = false,
                        query = ""
                    )
                }
/*            trackerUseCases
                .searchFood(state.query)
                .onSuccess { foods ->
                    state = state.copy(
                        trackableFood = foods.map { food ->
                            TrackableFoodUiState(food = food)
                        },
                        isSearching = false,
                        query = ""
                    )
                }
                .onFailure {
                    state = state.copy(isSearching = false)
                    _uiEvent.send(UiEvent.ShowSnackbar(UiText.StringResource(R.string.error_something_went_wrong)))
                }*/

        }
    }
}