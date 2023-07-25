package com.dojo.pokedex_domain.use_case

import com.dojo.pokedex_domain.repository.PokedexRepository

class SearchPokemonByName(
    val repository: PokedexRepository
) {
    operator fun invoke(name: String) = repository.searchPokemon(name)
}