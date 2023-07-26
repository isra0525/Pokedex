package com.dojo.pokedex_domain.di

import com.dojo.pokedex_domain.repository.PokedexRepository
import com.dojo.pokedex_domain.use_case.GetPokemonById
import com.dojo.pokedex_domain.use_case.GetStoredPokemons
import com.dojo.pokedex_domain.use_case.PokedexUseCases
import com.dojo.pokedex_domain.use_case.SearchPokemonByName
import com.dojo.pokedex_domain.use_case.StorePokemons
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PokedexDomainModule {

    @ViewModelScoped
    @Provides
    fun providePokedexUseCases(
        repository: PokedexRepository
    ): PokedexUseCases {
        return PokedexUseCases(
            storePokemons = StorePokemons(repository),
            searchPokemonByName = SearchPokemonByName(repository),
            getStoredPokemons = GetStoredPokemons(repository),
            getPokemonById = GetPokemonById(repository)
        )
    }

}