package com.dojo.pokedex_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dojo.pokedex_data.local.entity.*

@Database(
    entities = [
        PokemonEntity::class,
        TypeEntity::class,
        AbilityEntity::class,
        LocationAreaEntity::class,
        PokemonTypeCrossRef::class,
        PokemonAbilityCrossRef::class,
        PokemonLocationCrossRef::class
    ],
    version = 1
)
abstract class PokeDatabase: RoomDatabase() {
    abstract val dao: PokeDao
}