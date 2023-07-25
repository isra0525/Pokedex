package com.dojo.pokedex_data.mapper

import com.dojo.pokedex_data.local.entity.*
import com.dojo.pokedex_data.remote.dto.PokemonTypeDto
import com.dojo.pokedex_domain.model.*

fun PokemonEntity.ToPokemonDomain(
    listTypes: List<TypeEntity>,
    listAbilities: List<AbilityEntity>,
    listPlaces: List<LocationAreaEntity>): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        types = listTypes.map { it.ToPokemonTypeDomain() },
        evolvesTo = evolvesTo,
        abilities = listAbilities.map { it.ToPokemonAbilityDomain() },
        encounterPLaces = listPlaces.map { it.ToPokemonLocationAreaDomain() }
    )
}

fun TypeEntity.ToPokemonTypeDomain(): PokemonType {
    return PokemonType(
        name = this.name,
        url = this.url
    )
}

fun AbilityEntity.ToPokemonAbilityDomain(): PokemonAbility {
    return PokemonAbility(
        name = name,
        url = url
    )
}

fun LocationAreaEntity.ToPokemonLocationAreaDomain(): PokemonLocationArea {
    return PokemonLocationArea(
        name = name,
        url = url
    )
}
/*


fun PokemonEvolutionDto.ToPokemonEvolutionDomain(): PokemonEvolution {
    return PokemonEvolution(
        name = this.name,
        url = this.url
    )
}



*/
