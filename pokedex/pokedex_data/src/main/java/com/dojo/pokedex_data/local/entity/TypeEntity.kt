package com.dojo.pokedex_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type")
data class TypeEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val url: String
)

@Entity(
    tableName = "pokemon_type_cross_ref",
    primaryKeys = ["pokemonEntityId", "pokemonTypeEntityId"])
data class PokemonTypeCrossRef(
    val pokemonEntityId: Int,
    val pokemonTypeEntityId: Int
)
