package com.dojo.pokedex_data.local.entity

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemonEntity: PokemonEntity)

    @Query("""
        DELETE FROM pokemon
        """)
    suspend fun deleteAllPokemon()

    @Query("""
        SELECT * FROM pokemon
        """)
    fun getPokemons(): Flow<List<PokemonEntity>>

    // Type

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonType(typeEntity: TypeEntity)

    @Query("""
        DELETE FROM type
        """)
    suspend fun deleteAllPokemonType()

    @Query("""
        SELECT * FROM type
        """)
    fun getAllPokemonType(): Flow<List<TypeEntity>>

    @Query("""
        SELECT * FROM type
        WHERE name = :name
        """)
    fun getPokemonTypeByName(name: String): TypeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonTypeCrossRef(pokemonTypeCrossRef: PokemonTypeCrossRef)

    @Query("""
        SELECT * FROM type
        WHERE id IN (
            SELECT pokemonTypeEntityId FROM pokemon_type_cross_ref
            WHERE pokemonEntityId = :pokemonId
        )
        """)
    fun getPokemonTypeByPokemonId(pokemonId: Int): List<TypeEntity>

    // Ability

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbility(abilityEntity: AbilityEntity)

    @Query("""
        DELETE FROM ability
        """)
    suspend fun deleteAllAbility()

    @Query("""
        SELECT * FROM ability
        """)
    fun getAllAbility(): Flow<List<AbilityEntity>>

    @Query("""
        SELECT * FROM ability
        WHERE name = :name
        """)
    fun getAbilityByName(name: String): AbilityEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonAbilityCrossRef(pokemonAbilityCrossRef: PokemonAbilityCrossRef)

    @Query("""
        SELECT * FROM ability
        WHERE id IN (
            SELECT abilityEntityId FROM pokemon_ability_cross_ref
            WHERE pokemonEntityId = :pokemonId
        )
        """)
    fun getPokemonAbilityByPokemonId(pokemonId: Int): List<AbilityEntity>

    // LocationArea

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocationArea(locationAreaEntity: LocationAreaEntity)

    @Query("""
        DELETE FROM location_area
        """)
    suspend fun deleteAllLocationArea()

    @Query("""
        SELECT * FROM location_area
        """)
    fun getAllLocationArea(): Flow<List<LocationAreaEntity>>

    @Query("""
        SELECT * FROM location_area
        WHERE name = :name
        """)
    fun getLocationAreaByName(name: String): LocationAreaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonLocationAreaCrossRef(pokemonLocationCrossRef: PokemonLocationCrossRef)

    @Query("""
        SELECT * FROM location_area
        WHERE id IN (
            SELECT locationAreaEntityId FROM pokemon_location_cross_ref
            WHERE pokemonEntityId = :pokemonId
        )
        """)
    fun getPokemonLocationAreaByPokemonId(pokemonId: Int): List<LocationAreaEntity>

    @Query("""
        SELECT * FROM pokemon
        WHERE name like :name
        """)
    fun searchPokemonByName(name: String): List<PokemonEntity>
}
