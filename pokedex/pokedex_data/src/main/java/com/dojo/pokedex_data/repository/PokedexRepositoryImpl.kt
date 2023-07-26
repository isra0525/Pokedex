package com.dojo.pokedex_data.repository

import android.annotation.SuppressLint
import com.dojo.pokedex_data.local.entity.*
import com.dojo.pokedex_data.mapper.*
import com.dojo.pokedex_data.remote.PokemonApi
import com.dojo.pokedex_domain.model.Pokemon
import com.dojo.pokedex_domain.model.PokemonProgress
import com.dojo.pokedex_domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class PokedexRepositoryImpl(
    private val api: PokemonApi,
    private val dao: PokeDao
): PokedexRepository {

    @SuppressLint("NewApi")
    override fun storePokemons(quantity: Int): Flow<PokemonProgress> {
        val progressPerOnePercent = (100f/quantity)/100

        return flow {

            val pokemonList = api.getPokemons(quantity).pokemonItems
            var totalItems = 0
            pokemonList.forEach {
                totalItems++
                val pokemonDetail = api.getPokemonDetail(it.name)
                val details = pokemonDetail.types

                val pokemonEvolution = api.getPokemonEvolution(pokemonDetail.id)
                val pokemon  = pokemonDetail.toPokemonEntity(pokemonEvolution)
                dao.insertPokemon(pokemon)

                details.forEach { det ->
                    var insertedRel = false

                    dao.getPokemonTypeByName(det.type.name)?.let { typeFound ->
                        typeFound.id?.let { idEntity ->
                            insertedRel = true
                            dao.insertPokemonTypeCrossRef( PokemonTypeCrossRef(pokemonDetail.id, idEntity) )
                        }
                    }?: run {
                        dao.insertPokemonType(det.toPokemonType())
                    }

                    if (!insertedRel) dao.getPokemonTypeByName(det.type.name)?.let { typeFound ->
                        typeFound.id?.let { idEntity ->
                            dao.insertPokemonTypeCrossRef( PokemonTypeCrossRef(pokemonDetail.id, idEntity) )
                        }
                    }
                }
                val abilities = pokemonDetail.abilities
                abilities.forEach { abi ->
                    var insertedRel = false
                    dao.getAbilityByName(abi.ability.name)?.let { abilityFound ->
                        abilityFound.id?.let { idEntity ->
                            insertedRel = true
                            dao.insertPokemonAbilityCrossRef(PokemonAbilityCrossRef(pokemonDetail.id, idEntity) )
                        }
                    }?: run {
                        dao.insertAbility(AbilityEntity(name = abi.ability.name, url = abi.ability.url))
                    }
                    if (!insertedRel) dao.getAbilityByName(abi.ability.name)?.let { abilityFound ->
                        abilityFound.id?.let { idEntity ->
                            dao.insertPokemonAbilityCrossRef(PokemonAbilityCrossRef(pokemonDetail.id, idEntity) )
                        }
                    }

                }
                val pokemonLocation = api.getPokemonLocation(pokemonDetail.id)
                pokemonLocation.forEach { loc ->
                    var insertedRel = false
                    dao.getLocationAreaByName(loc.locationArea.name)?.let { locationFound ->
                        locationFound.id?.let { idEntity ->
                            insertedRel = true
                            dao.insertPokemonLocationAreaCrossRef(PokemonLocationCrossRef(pokemonDetail.id, idEntity) )
                        }
                    }?: run {
                        dao.insertLocationArea(LocationAreaEntity(name = loc.locationArea.name, url = loc.locationArea.url))
                    }
                    if (!insertedRel) dao.getLocationAreaByName(loc.locationArea.name)?.let { locationFound ->
                        locationFound.id?.let { idEntity ->
                            dao.insertPokemonLocationAreaCrossRef(PokemonLocationCrossRef(pokemonDetail.id, idEntity) )
                        }
                    }
                }
                emit(PokemonProgress(progressPerOnePercent*totalItems,it.name))
            }
        }
    }

    override fun searchPokemon(name: String): Result<List<Pokemon>> {
        try {
            if(name.isBlank()) {
                return Result.success(emptyList())
            }
            val list  = dao.searchPokemonByName(name).map {
                        val listTypes = dao.getPokemonTypeByPokemonId(it.id)
                        val listAbilities = dao.getPokemonAbilityByPokemonId(it.id)
                        val listLocation = dao.getPokemonLocationAreaByPokemonId(it.id)
                        it.ToPokemonDomain(listTypes, listAbilities, listLocation)
                    }
            return Result.success(list)

        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure(e)
        }
    }

    override fun getPokemons(): Flow<List<Pokemon>> {
        return dao.getPokemons().map { it.map { pokemon ->
            val listTypes = dao.getPokemonTypeByPokemonId(pokemon.id)
            val listAbilities = dao.getPokemonAbilityByPokemonId(pokemon.id)
            val listLocation = dao.getPokemonLocationAreaByPokemonId(pokemon.id)
            pokemon.ToPokemonDomain(listTypes, listAbilities, listLocation)
        } }
    }

    override fun getPokemonById(id: Int): Pokemon {
        return dao.getPokemonById(id).let {
            val listTypes = dao.getPokemonTypeByPokemonId(it.id)
            val listAbilities = dao.getPokemonAbilityByPokemonId(it.id)
            val listLocation = dao.getPokemonLocationAreaByPokemonId(it.id)
            it.ToPokemonDomain(listTypes, listAbilities, listLocation)
        }
    }


}