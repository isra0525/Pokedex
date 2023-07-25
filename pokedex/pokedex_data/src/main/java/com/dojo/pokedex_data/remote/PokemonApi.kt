package com.dojo.pokedex_data.remote

import com.dojo.pokedex_data.remote.dto.PokemonDetailDto
import com.dojo.pokedex_data.remote.dto.PokemonDto
import com.dojo.pokedex_data.remote.dto.PokemonEvolutionDto
import com.dojo.pokedex_data.remote.dto.PokemonLocationDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("api/v2/pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int = 0
    ): PokemonDto

    @GET("api/v2/pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): PokemonDetailDto

    @GET("api/v2/pokemon/{id}/encounters")
    suspend fun getPokemonLocation(
        @Path("id") id: Int
    ): List<PokemonLocationDto>

    @GET("api/v2/evolution-chain/{id}/")
    suspend fun getPokemonEvolution(
        @Path("id") id: Int
    ): PokemonEvolutionDto

    companion object {
        const val BASE_URL = "https://pokeapi.co/"
    }
}