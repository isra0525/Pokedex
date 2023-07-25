package com.dojo.pokedex_data.remote.dto

data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val types: List<PokemonTypeItemDto>,
    val abilities: List<PokemonAbilityItemDto>,
    //location_area_encounters is through api
    //same for evolutions
    )

data class PokemonTypeItemDto(
    val slot: Int,
    val type: PokemonTypeDto
)

data class PokemonTypeDto(
    val name: String,
    val url: String
)

data class PokemonAbilityItemDto(
    val slot: Int,
    val ability: PokemonAbilityDto
)

data class PokemonAbilityDto(
    val name: String,
    val url: String
)
