package com.dojo.pokedex_presentation.detail

sealed class DetailEvent {
    data class OnLoadPokemon(val id: Int): DetailEvent()
}
