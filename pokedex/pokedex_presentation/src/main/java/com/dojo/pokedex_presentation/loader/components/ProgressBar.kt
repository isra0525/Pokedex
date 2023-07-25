package com.dojo.pokedex_presentation.loader.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dojo.pokedex_presentation.loader.LoaderState

@Composable
fun PokemonProgressBar(
    loadstate: LoaderState,
    modifier: Modifier = Modifier
) {
    val background = MaterialTheme.colors.primary
    val progressColor = MaterialTheme.colors.primaryVariant
    val progressValue = remember {
        Animatable(0.1f)
    }

    LaunchedEffect(key1 = loadstate) {
        progressValue.animateTo(
            targetValue = loadstate.progress,
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFD0165))
            .border(
                width = 5.dp,
                color = Color.Black
            )
            .padding(horizontal = 20.dp)
        ,
        contentAlignment = Alignment.Center
    ) {

        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .drawBehind {
                drawRoundRect(
                    color = Color.Black,
                    size = size,
                    cornerRadius = CornerRadius(60f),
                )
            }

        ) {
            drawRoundRect(
                topLeft =Offset(23f,20f),
                color = Color.White,
                        size = Size(
                    width = (1f * size.width)-(60/2)-15,
                    height = size.height/2
                ),
                cornerRadius = CornerRadius(60f)
            )
        }
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
        ) {
            drawRoundRect(
                topLeft =Offset(23f,20f),
                color = Color.Green,
                size = Size(
                    width = (progressValue.value * size.width)-(60/2)-15,
                    height = size.height/2
                ),
                cornerRadius = CornerRadius(60f)
            )
        }
        Text(
            text = "Loading : ${loadstate.pokemonName}",
            color = Color.Black,
            fontSize = 10.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
    }
}

@Composable
fun PokeProgressBar(
    loadstate: LoaderState,
) {
    println(loadstate.pokemonName)
    val progressValue = remember {
        Animatable(0.0f)
    }

    LaunchedEffect(key1 = loadstate) {
        progressValue.animateTo(
            targetValue = loadstate.progress,
        )
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .drawBehind {
                drawRoundRect(
                    color = Color.Black,
                    size = size,
                    cornerRadius = CornerRadius(60f),
                )
            }

        ) {
            drawRoundRect(
                topLeft =Offset(23f,20f),
                color = Color.White,
                size = Size(
                    width = (1f * size.width)-(60/2)-15,
                    height = size.height/2
                ),
                cornerRadius = CornerRadius(60f)
            )
        }
        if (progressValue.value > 0.0f) {
            Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
            ) {
                drawRoundRect(
                    topLeft =Offset(23f,20f),
                    color = Color.Green,
                    size = Size(
                        width = (progressValue.value * size.width)-(60/2)-15,
                        height = size.height/2
                    ),
                    cornerRadius = CornerRadius(60f)
                )
            }
            Text(
                text = "Loading : ${loadstate.pokemonName}",
                color = Color.Black,
                fontSize = 10.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        }
    }
}


/*
@Preview(
    showBackground = true
)
@Composable
fun PokemonProgressBarPreview() {
    PokemonProgressBar(
        loadstate = IndexerState(
            progress = 0.56f
        ),
        Modifier
            .fillMaxWidth()
            .height(50.dp)
    )
}*/

@Preview(
    showBackground = false
)
@Composable
fun PokemonProgressBarPreview() {
    PokeProgressBar(
        loadstate = LoaderState(
            progress = 0.7f,
            pokemonName = "Pikachu"
        )
    )
}
