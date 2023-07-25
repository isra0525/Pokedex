package com.dojo.pokedex_presentation.loader

sealed class LoaderEvent {
    data class OnLoadPokemons(val quantity: Int): LoaderEvent()
    object OnLoadFinishedRedirect: LoaderEvent()
}