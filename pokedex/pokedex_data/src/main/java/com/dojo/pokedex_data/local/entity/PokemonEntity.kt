package com.dojo.pokedex_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val evolvesTo: String = "",
/*    val types: List<PokemonTypeData>,
    val abilities: List<PokemonAbilityItem>,
    val encounterPLaces: List<LocationAreaData>*/
)
