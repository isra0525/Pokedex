package com.dojo.pokedex_presentation.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dojo.core.util.UiEvent
import com.dojo.pokedex_presentation.loader.components.PokeProgressBar

@Composable
fun LoaderScreen(
    quantity: Int,
    onNextClick: () -> Unit,
    viewModel: LoaderViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(key1 = quantity) {
        viewModel.onEvent(LoaderEvent.OnLoadPokemons(quantity))

    }
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            println("estado")
            println(event)
            when(event) {
                is UiEvent.Success -> onNextClick()
                else -> Unit
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.Center
    ){
        item {
            Text(text = "Loading...")
            PokeProgressBar(loadstate = state)
        }
    }


/*    LazyColumn(modifier = Modifier
        .fillMaxSize()
    ) {
        item {
            PokemonProgressBar(progress = state.progress, pokemonName = state.pokemonName)
        }
    }*/

}