package com.dojo.pokedex_data.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.dojo.pokedex_data.local.PokeDatabase
import com.dojo.pokedex_data.remote.PokemonApi
import com.dojo.pokedex_data.repository.PokedexRepositoryImpl
import com.dojo.pokedex_domain.repository.PokedexRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import kotlin.properties.ReadOnlyProperty

@Module
@InstallIn(SingletonComponent::class)
class PokedexDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonApi(client: OkHttpClient): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(PokemonApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonDataBase(app: Application) : PokeDatabase {
        return Room.databaseBuilder(
            app,
            PokeDatabase::class.java,
            "poke_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProkemonUseCases(
        api: PokemonApi,
        db: PokeDatabase
    ): PokedexRepository {
        return PokedexRepositoryImpl(
            api,
            db.dao
        )
    }

    //no se usa
    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): ReadOnlyProperty<Context, DataStore<Preferences>> = preferencesDataStore(name = "pokemon_prefs")
}