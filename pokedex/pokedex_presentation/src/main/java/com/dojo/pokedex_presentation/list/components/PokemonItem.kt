package com.dojo.pokedex_presentation.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.dojo.pokedex_domain.model.Pokemon
import com.dojo.pokedex_domain.model.PokemonAbility
import com.dojo.pokedex_domain.model.PokemonLocationArea
import com.dojo.pokedex_domain.model.PokemonType

@Composable
fun PokemonItem(
    pokemon: Pokemon 
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(
                bottomStart = 20.dp
            ))
            .shadow(2.dp, RoundedCornerShape(
                bottomStart = 20.dp
            ))
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(
                    bottomStart = 20.dp
                )
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png")
                        .apply<ImageRequest.Builder>(block = fun ImageRequest.Builder.() {
                            crossfade(true)
/*                            error(R)
                            fallback(R.drawable.pokemon)*/
                        }).build()
                ),
                contentDescription = pokemon.name,
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
                text = pokemon.name,
                color = Color.Black
            )
        }

    }
}

@Preview(
    showBackground = true
)
@Composable
fun PokemonItemPreview() {
    val poke = Pokemon(
        1,
        "Pikachu",
        "Raychu",
        listOf(PokemonType("fire","sdfgsd")),
        listOf(PokemonAbility("bla bla", "asfasdf")),
        listOf(PokemonLocationArea("magisterio", "sdfgsdfg"))
    )
    
    PokemonItem(pokemon = poke)

}