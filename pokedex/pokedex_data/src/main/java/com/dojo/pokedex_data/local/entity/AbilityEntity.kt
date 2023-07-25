package com.dojo.pokedex_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ability")
data class AbilityEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val url: String
)

@Entity(
    tableName = "pokemon_ability_cross_ref",
    primaryKeys = ["pokemonEntityId", "abilityEntityId"])
data class PokemonAbilityCrossRef(
    val pokemonEntityId: Int,
    val abilityEntityId: Int
)
