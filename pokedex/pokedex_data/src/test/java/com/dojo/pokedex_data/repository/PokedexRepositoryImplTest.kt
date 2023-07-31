package com.dojo.pokedex_data.repository

import com.dojo.pokedex_data.local.entity.PokeDao
import com.dojo.pokedex_data.remote.PokemonApi
import com.dojo.pokedex_data.remote.dto.EvolvesTo
import com.dojo.pokedex_data.remote.dto.EvolvesToItem
import com.dojo.pokedex_data.remote.dto.LocationAreaDto
import com.dojo.pokedex_data.remote.dto.PokemonAbilityDto
import com.dojo.pokedex_data.remote.dto.PokemonAbilityItemDto
import com.dojo.pokedex_data.remote.dto.PokemonDetailDto
import com.dojo.pokedex_data.remote.dto.PokemonDto
import com.dojo.pokedex_data.remote.dto.PokemonEvolutionDto
import com.dojo.pokedex_data.remote.dto.PokemonItem
import com.dojo.pokedex_data.remote.dto.PokemonLocationDto
import com.dojo.pokedex_data.remote.dto.PokemonTypeDto
import com.dojo.pokedex_data.remote.dto.PokemonTypeItemDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PokedexRepositoryImplTest {

    private lateinit var pokedexRepositoryImpl: PokedexRepositoryImpl

    @Before
    fun setUp() {
        val api = mockk<PokemonApi>(relaxed = true)
        val dao = mockk<PokeDao>(relaxed = true)
        pokedexRepositoryImpl = PokedexRepositoryImpl(api, dao)

        coEvery { api.getPokemons(1) } returns PokemonDto(pokemonItems = listOf(
            PokemonItem("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/")
        ))

        coEvery { api.getPokemons(5) } returns PokemonDto(pokemonItems = listOf(
            PokemonItem("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
            PokemonItem("ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"),
            PokemonItem("venusaur", "https://pokeapi.co/api/v2/pokemon/3/"),
            PokemonItem("charmander", "https://pokeapi.co/api/v2/pokemon/4/"),
            PokemonItem("charmeleon", "https://pokeapi.co/api/v2/pokemon/5/")
        ))

        coEvery { api.getPokemonDetail("bulbasaur") } returns PokemonDetailDto(
            id = 1,
            name = "bulbasaur",
            types = listOf(
                PokemonTypeItemDto(
                    slot = 1,
                    type = PokemonTypeDto(
                        name = "grass",
                        url = "https://pokeapi.co/api/v2/type/12/"
                    )
                ),
                PokemonTypeItemDto(
                    slot = 2,
                    type = PokemonTypeDto(
                        name = "poison",
                        url = "https://pokeapi.co/api/v2/type/4/"
                    )
                )
            ),
            abilities = listOf(
                PokemonAbilityItemDto(
                    slot = 1,
                    ability = PokemonAbilityDto(
                        name = "overgrow",
                        url = "https://pokeapi.co/api/v2/ability/65/"
                    )
                ),
                PokemonAbilityItemDto(
                    slot = 3,
                    ability = PokemonAbilityDto(
                        name = "chlorophyll",
                        url = "https://pokeapi.co/api/v2/ability/34/"
                    )
                )
            )
        )

        coEvery { api.getPokemonDetail("ivysaur") } returns PokemonDetailDto(
            id = 2,
            name = "ivysaur",
            types = listOf(
                PokemonTypeItemDto(
                    slot = 1,
                    type = PokemonTypeDto(
                        name = "grass",
                        url = "https://pokeapi.co/api/v2/type/12/"
                    )
                ),
                PokemonTypeItemDto(
                    slot = 2,
                    type = PokemonTypeDto(
                        name = "poison",
                        url = "https://pokeapi.co/api/v2/type/4/"
                    )
                )
            ),
            abilities = listOf(
                PokemonAbilityItemDto(
                    slot = 1,
                    ability = PokemonAbilityDto(
                        name = "overgrow",
                        url = "https://pokeapi.co/api/v2/ability/65/"
                    )
                ),
                PokemonAbilityItemDto(
                    slot = 3,
                    ability = PokemonAbilityDto(
                        name = "chlorophyll",
                        url = "https://pokeapi.co/api/v2/ability/34/"
                    )
                )
            )
        )

        coEvery { api.getPokemonDetail("venusaur") } returns PokemonDetailDto(
            id = 3,
            name = "venusaur",
            types = listOf(
                PokemonTypeItemDto(
                    slot = 1,
                    type = PokemonTypeDto(
                        name = "grass",
                        url = "https://pokeapi.co/api/v2/type/12/"
                    )
                ),
                PokemonTypeItemDto(
                    slot = 2,
                    type = PokemonTypeDto(
                        name = "poison",
                        url = "https://pokeapi.co/api/v2/type/4/"
                    )
                )
            ),
            abilities = listOf(
                PokemonAbilityItemDto(
                    slot = 1,
                    ability = PokemonAbilityDto(
                        name = "overgrow",
                        url = "https://pokeapi.co/api/v2/ability/65/"
                    )
                ),
                PokemonAbilityItemDto(
                    slot = 3,
                    ability = PokemonAbilityDto(
                        name = "chlorophyll",
                        url = "https://pokeapi.co/api/v2/ability/34/"
                    )
                )
            )
        )

        coEvery { api.getPokemonDetail("charmander") } returns PokemonDetailDto(
            id = 4,
            name = "charmander",
            types = listOf(
                PokemonTypeItemDto(
                    slot = 1,
                    type = PokemonTypeDto(
                        name = "fire",
                        url = "https://pokeapi.co/api/v2/type/10/"
                    )
                )
            ),
            abilities = listOf(
                PokemonAbilityItemDto(
                    slot = 1,
                    ability = PokemonAbilityDto(
                        name = "blaze",
                        url = "https://pokeapi.co/api/v2/ability/66/"
                    )
                ),
                PokemonAbilityItemDto(
                    slot = 3,
                    ability = PokemonAbilityDto(
                        name = "solar-power",
                        url = "https://pokeapi.co/api/v2/ability/94/"
                    )
                )
            )
        )

        coEvery { api.getPokemonDetail("charmeleon") } returns PokemonDetailDto(
            id = 5,
            name = "charmeleon",
            types = listOf(
                PokemonTypeItemDto(
                    slot = 1,
                    type = PokemonTypeDto(
                        name = "fire",
                        url = "https://pokeapi.co/api/v2/type/10/"
                    )
                )
            ),
            abilities = listOf(
                PokemonAbilityItemDto(
                    slot = 1,
                    ability = PokemonAbilityDto(
                        name = "blaze",
                        url = "https://pokeapi.co/api/v2/ability/66/"
                    )
                ),
                PokemonAbilityItemDto(
                    slot = 3,
                    ability = PokemonAbilityDto(
                        name = "solar-power",
                        url = "https://pokeapi.co/api/v2/ability/94/"
                    )
                )
            )
        )

        coEvery { api.getPokemonEvolution(1)} returns PokemonEvolutionDto(
            chain = EvolvesToItem(
                evolvesTo = listOf(
                    EvolvesTo(
                        species = PokemonItem(
                            name = "ivysaur",
                            url = "https://pokeapi.co/api/v2/pokemon/2/"
                        ),
                    )
                )
            )
        )

        coEvery { api.getPokemonEvolution(2)} returns PokemonEvolutionDto(
            chain = EvolvesToItem(
                evolvesTo = listOf(
                    EvolvesTo(
                        species = PokemonItem(
                            name = "venusaur",
                            url = "https://pokeapi.co/api/v2/pokemon/3/"
                        ),
                    )
                )
            )
        )

        coEvery { api.getPokemonEvolution(4)} returns PokemonEvolutionDto(
            chain = EvolvesToItem(
                evolvesTo = listOf(
                    EvolvesTo(
                        species = PokemonItem(
                            name = "charmeleon",
                            url = "https://pokeapi.co/api/v2/pokemon/5/"
                        ),
                    )
                )
            )
        )

        coEvery { api.getPokemonEvolution(5)} returns PokemonEvolutionDto(
            chain = EvolvesToItem(
                evolvesTo = listOf(
                    EvolvesTo(
                        species = PokemonItem(
                            name = "charizard",
                            url = "https://pokeapi.co/api/v2/pokemon/6/"
                        ),
                    )
                )
            )
        )

        coEvery { api.getPokemonLocation(1) } returns listOf(
            PokemonLocationDto(
                locationArea = LocationAreaDto(
                    name = "cerulean-city-area",
                    url = "https://pokeapi.co/api/v2/location-area/281/"
                )
            ),
            PokemonLocationDto(
                locationArea = LocationAreaDto(
                    name = "pallet-town-area",
                    url = "https://pokeapi.co/api/v2/location-area/285/"
                )
            ),
            PokemonLocationDto(
                locationArea = LocationAreaDto(
                    name = "lumiose-city-area",
                    url = "https://pokeapi.co/api/v2/location-area/779/"
                )
            )
        )

        coEvery { api.getPokemonLocation(2) } returns listOf(
            PokemonLocationDto(
                locationArea = LocationAreaDto(
                    name = "cerulean-city-area",
                    url = "https://pokeapi.co/api/v2/location-area/281/"
                )
            ),
            PokemonLocationDto(
                locationArea = LocationAreaDto(
                    name = "pallet-town-area",
                    url = "https://pokeapi.co/api/v2/location-area/285/"
                )
            ),
            PokemonLocationDto(
                locationArea = LocationAreaDto(
                    name = "lumiose-city-area",
                    url = "https://pokeapi.co/api/v2/location-area/779/"
                )
            )
        )

        coEvery { api.getPokemonLocation(3) } returns listOf(
            PokemonLocationDto(
                locationArea = LocationAreaDto(
                    name = "cerulean-city-area",
                    url = "https://pokeapi.co/api/v2/location-area/281/"
                )
            ),
            PokemonLocationDto(
                locationArea = LocationAreaDto(
                    name = "pallet-town-area",
                    url = "https://pokeapi.co/api/v2/location-area/285/"
                )
            ),
            PokemonLocationDto(
                locationArea = LocationAreaDto(
                    name = "lumiose-city-area",
                    url = "https://pokeapi.co/api/v2/location-area/779/"
                )
            )
        )

        coEvery { api.getPokemonLocation(4) } returns listOf(
            PokemonLocationDto(
                locationArea = LocationAreaDto(
                    name = "cerulean-city-area",
                    url = "https://pokeapi.co/api/v2/location-area/281/"
                )
            ),
            PokemonLocationDto(
                locationArea = LocationAreaDto(
                    name = "pallet-town-area",
                    url = "https://pokeapi.co/api/v2/location-area/285/"
                )
            ),
            PokemonLocationDto(
                locationArea = LocationAreaDto(
                    name = "lumiose-city-area",
                    url = "https://pokeapi.co/api/v2/location-area/779/"
                )
            )
        )

        coEvery { api.getPokemonLocation(5) } returns listOf(
            PokemonLocationDto(
                locationArea = LocationAreaDto(
                    name = "cerulean-city-area",
                    url = "https://pokeapi.co/api/v2/location-area/281/"
                )
            ),
            PokemonLocationDto(
                locationArea = LocationAreaDto(
                    name = "pallet-town-area",
                    url = "https://pokeapi.co/api/v2/location-area/285/"
                )
            ),
            PokemonLocationDto(
                locationArea = LocationAreaDto(
                    name = "lumiose-city-area",
                    url = "https://pokeapi.co/api/v2/location-area/779/"
                )
            )
        )


    }

    @Test
    fun `store 1 pokemons and return one flow`() = runBlocking {
        val flows = pokedexRepositoryImpl.storePokemons(1)
        val pokemon = flows.count()
        assert(pokemon == 1)
    }

    @Test
    fun `store 5 pokemons and return five flows`() = runBlocking {
        val flows = pokedexRepositoryImpl.storePokemons(5)
        val pokemon = flows.count()
        assert(pokemon == 5)
    }


}