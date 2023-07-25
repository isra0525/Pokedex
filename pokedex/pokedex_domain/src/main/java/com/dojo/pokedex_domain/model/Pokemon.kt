package com.dojo.pokedex_domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val evolvesTo: String,
    val types: List<PokemonType>,
    val abilities: List<PokemonAbility>,
    val encounterPLaces: List<PokemonLocationArea>
)