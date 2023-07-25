package com.dojo.pokedex_data.remote.dto

import com.squareup.moshi.Json

data class PokemonLocationDto(
    @field:Json(name = "location_area")
    val locationArea: LocationAreaDto,
)

data class LocationAreaDto(
    val name: String,
    val url: String
)
