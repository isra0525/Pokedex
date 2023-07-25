package com.dojo.pokedex_presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemInfo
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dojo.pokedex_presentation.list.components.PokemonItem
import com.dojo.pokedex_presentation.list.components.PokemonItemGrid
import com.dojo.pokedex_presentation.list.components.SearchTextField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val keyBoardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchTextField(
            text = state.query,
            onValueChange = {
                viewModel.onEvent(ListEvent.OnQueryChange(it))
            },
            shouldShowHint = state.isHintVisible,
            onSearch = {
                keyBoardController?.hide()
                viewModel.onEvent(ListEvent.OnSearch)
            },
            onFocusChanged = {
                viewModel.onEvent(ListEvent.OnSearchFocusChange(it.isFocused))
            }
        )
        LazyVerticalGrid(
            modifier = Modifier.padding(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            columns = GridCells.Adaptive(minSize = 150.dp),
        ) {
            items(state.pokemons) {
                PokemonItemGrid(pokemon = it)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
/*    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        items(state.pokemons) {
            PokemonItem(pokemon = it)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }*/
}