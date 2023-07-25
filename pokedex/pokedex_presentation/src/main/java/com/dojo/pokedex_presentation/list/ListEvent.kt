package com.dojo.pokedex_presentation.list

import com.dojo.pokedex_domain.model.Pokemon

sealed class ListEvent {
    object OnLoadPokemons: ListEvent()
    data class OnClickPokemon(val pokemon: Pokemon): ListEvent()
    data class OnQueryChange(val query: String): ListEvent()
    object OnSearch: ListEvent()
    data class OnSearchFocusChange(val isFocussed: Boolean): ListEvent()



}