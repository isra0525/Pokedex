package com.dojo.pokedex_presentation.list

import androidx.compose.runtime.collectAsState
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCases: PokedexUseCases
): ViewModel() {
    var state = MutableStateFlow(ListState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ListEvent) {
        when(event) {
            is ListEvent.OnLoadPokemons -> {
                execGetPokemonsStored()
            }
            is ListEvent.OnClickPokemon -> {
            }
            is ListEvent.OnQueryChange -> {
                state.value = state.value.copy(query = event.query)
            }
            is ListEvent.OnSearch -> executeSearch()
            is ListEvent.OnSearchFocusChange -> {
                state.value = state.value.copy(
                    isHintVisible = !event.isFocussed && state.value.query.isBlank(),
                    pokemons = emptyList()
                )
            }
        }
    }

    fun execGetPokemonsStored(){
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getStoredPokemons().collect { pokemonList ->
                state.value = state.value.copy(
                    pokemons = pokemonList
                )
            }
        }
    }

    private fun executeSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchPokemonByName(state.value.query)
                .onSuccess { pokemons ->
                    println(pokemons)
                    state.value = state.value.copy(
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