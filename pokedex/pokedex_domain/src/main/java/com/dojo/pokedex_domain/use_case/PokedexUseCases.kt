package com.dojo.pokedex_domain.use_case

data class PokedexUseCases(
    val storePokemons: StorePokemons,
    val searchPokemonByName: SearchPokemonByName,
    val getStoredPokemons: GetStoredPokemons
)
