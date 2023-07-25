package com.dojo.pokedex_presentation.list.components

import com.dojo.pokedex_domain.model.Pokemon

data class ListState(
    val pokemons: List<Pokemon> = emptyList(),
    val isSearching: Boolean = false,
    val isHintVisible: Boolean = false,
    val query: String = ""
)