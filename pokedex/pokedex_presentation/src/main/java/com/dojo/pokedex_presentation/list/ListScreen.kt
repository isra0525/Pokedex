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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dojo.pokedex_presentation.list.components.PokemonItem
import com.dojo.pokedex_presentation.list.components.PokemonItemGrid
import com.dojo.pokedex_presentation.list.components.SearchTextField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ListScreen(
    OnNavigateToDetail: (Int) -> Unit,
    viewModel: ListViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val keyBoardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = true) {
        viewModel.onEvent(ListEvent.OnLoadPokemons)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        SearchTextField(
            modifier = Modifier.semantics {
              contentDescription = "InputSearch"
            },
            text = state.value.query,
            onValueChange = {
                viewModel.onEvent(ListEvent.OnQueryChange(it))
            },
            shouldShowHint = state.value.isHintVisible,
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
            items(state.value.pokemons) {
                PokemonItemGrid(
                    pokemon = it,
                    OnClickPokemon = { OnNavigateToDetail(it.id) }
                )
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