package com.dojo.pokedex_data.mapper

import com.dojo.pokedex_data.local.entity.*
import com.dojo.pokedex_data.remote.dto.*

fun PokemonDetailDto.toPokemonEntity(
    evolution: PokemonEvolutionDto
): PokemonEntity {
    val evolvesTo = if (evolution.chain.evolvesTo.isNotEmpty()) {
        evolution.chain.evolvesTo.first().species.name
    } else ""
    return PokemonEntity(
        id = id,
        name = name,
        evolvesTo = evolvesTo
/*        abilities = abilities,
        types = types.map { it.toPokemonType() },
        encounterPLaces = locations.map { it.toLocationArea() }*/
    )
}

fun PokemonTypeItemDto.toPokemonType(): TypeEntity {
    return TypeEntity(
        name = type.name,
        url = type.url
    )
}

fun PokemonAbilityDto.toPokemonAbility(): AbilityEntity {
    return AbilityEntity(
        name = name,
        url = url
    )
}

fun LocationAreaDto.toLocationArea(): LocationAreaEntity {
    return LocationAreaEntity(
        name = name,
        url = url
    )
}

/*
fun PokemonEvolutionDto.toPokemonEvolution(): PokemonEvolutionDto {
    return PokemonEvolutionDto(
        name = chain.evolvesTo.first().species.name,
        url = chain.evolvesTo.first().species.url
    )
}*/
