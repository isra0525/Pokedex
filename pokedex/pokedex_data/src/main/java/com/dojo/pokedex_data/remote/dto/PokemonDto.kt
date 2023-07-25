package com.dojo.pokedex_data.remote.dto

import com.squareup.moshi.Json

data class PokemonDto(
    @field:Json(name = "results")
    val pokemonItems: List<PokemonItem>,
)
