package com.dojo.pokedex_data.remote.dto

import com.squareup.moshi.Json

data class PokemonEvolutionDto(
    val chain: EvolvesToItem
)

data class EvolvesToItem(
    @field:Json(name = "evolves_to")
    val evolvesTo: List<EvolvesTo>
)

data class EvolvesTo(
    val species: PokemonItem
)