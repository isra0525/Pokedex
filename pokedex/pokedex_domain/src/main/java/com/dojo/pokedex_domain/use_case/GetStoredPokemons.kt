package com.dojo.pokedex_domain.use_case

import com.dojo.pokedex_domain.model.Pokemon
import com.dojo.pokedex_domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow

class GetStoredPokemons(
    val repository: PokedexRepository
    ) {

    operator fun invoke(): Flow<List<Pokemon>> {
        return repository.getPokemons()
    }
}