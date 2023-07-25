package com.dojo.pokedex_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_area")
data class LocationAreaEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val url: String
)

@Entity(
    tableName = "pokemon_location_cross_ref",
    primaryKeys = ["pokemonEntityId", "locationAreaEntityId"])
data class PokemonLocationCrossRef(
    val pokemonEntityId: Int,
    val locationAreaEntityId: Int
)
