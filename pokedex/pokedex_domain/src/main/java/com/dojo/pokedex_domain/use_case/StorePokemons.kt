package com.dojo.pokedex_domain.use_case

import com.dojo.pokedex_domain.model.PokemonProgress
import com.dojo.pokedex_domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow

class StorePokemons(
    private val repository: PokedexRepository
) {
    operator fun invoke(quantity: Int): Flow<PokemonProgress> {
        return repository.storePokemons(quantity)
    }
}