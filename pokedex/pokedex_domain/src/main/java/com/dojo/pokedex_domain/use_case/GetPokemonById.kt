package com.dojo.pokedex_domain.use_case

import com.dojo.pokedex_domain.repository.PokedexRepository

class GetPokemonById(
    val repository: PokedexRepository
) {
    operator fun invoke(id: Int) = repository.getPokemonById(id)
}