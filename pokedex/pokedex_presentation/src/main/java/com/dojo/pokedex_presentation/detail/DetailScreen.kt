package com.dojo.pokedex_presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.dojo.pokedex_presentation.list.ListEvent

@Composable
fun DetailScreen(
    pokemonId: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val pokemon = viewModel.state.value.pokemon
    LaunchedEffect(key1 = true) {
        viewModel.onEvent(DetailEvent.OnLoadPokemon(pokemonId))
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .clip(
                RoundedCornerShape(
                    bottomStart = 20.dp
                )
            )
            .shadow(
                2.dp, RoundedCornerShape(
                    bottomStart = 20.dp
                )
            )
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(
                    bottomStart = 20.dp
                )
            ),
    ) {
        pokemon?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon?.id}.png")
                            .apply<ImageRequest.Builder>(block = fun ImageRequest.Builder.() {
                                crossfade(true)
                                /*                            error(R)
                                                            fallback(R.drawable.pokemon)*/
                            }).build()
                    ),
                    contentDescription = pokemon?.name,
                    modifier = Modifier
                        .height(130.dp)
                        .aspectRatio(1f)
                        .clip(
                            RoundedCornerShape(
                                topStart = 5.dp,
                                bottomStart = 5.dp
                            )
                        )
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(10f)
                        )
                )
                Text(
                    text = it.name,
                    color = Color.Black
                )
                Row {
                    Column {
                        Text(
                            text = "EvolvesTo:",
                            color = Color.Black
                        )
                    }
                    Column {
                        Text(
                            text = it.evolvesTo,
                            color = Color.Black
                        )
                    }
                }
                Row {
                    Column() {
                        Text(
                            text = "Types:",
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        it.types.forEach {
                            Text(
                                text = it.name,
                                color = Color.Black
                            )
                        }

                    }
                }
                Row {
                    Column {
                        Text(
                            text = "Abilities:",
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        it.abilities.forEach {
                            Text(
                                text = it.name,
                                color = Color.Black
                            )
                        }
                    }
                }
                Row {
                    Column {
                        Text(
                            text = "Encounter Places:",
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        it.encounterPLaces.forEach {
                            Text(
                                text = it.name,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }

    }

}