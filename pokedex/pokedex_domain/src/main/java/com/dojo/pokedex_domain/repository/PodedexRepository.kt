package com.dojo.pokedex_domain.repository

import com.dojo.pokedex_domain.model.Pokemon
import com.dojo.pokedex_domain.model.PokemonProgress
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {

    fun storePokemons(quantity: Int): Flow<PokemonProgress>

    fun searchPokemon(name: String): Result<List<Pokemon>>

    fun getPokemons(): Flow<List<Pokemon>>

}