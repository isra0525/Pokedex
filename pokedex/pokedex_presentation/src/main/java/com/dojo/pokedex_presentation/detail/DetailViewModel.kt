package com.dojo.pokedex_presentation.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.pokedex_domain.use_case.PokedexUseCases
import com.dojo.pokedex_presentation.list.ListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val useCases: PokedexUseCases
): ViewModel() {
    val state = mutableStateOf(DetailState())

    fun onEvent(event: DetailEvent) {
        when(event) {
            is DetailEvent.OnLoadPokemon -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val pokemon = useCases.getPokemonById(event.id)
                    state.value = state.value.copy(pokemon = pokemon)
                }
            }
        }

    }


}