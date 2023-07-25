package com.dojo.pokedex

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dojo.core.navigation.Route
import com.dojo.pokedex.ui.theme.PokedexTheme
import com.dojo.pokedex_presentation.list.ListScreen
import com.dojo.pokedex_presentation.loader.LoaderScreen
import com.dojo.pokedex_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState) {

                    NavHost(
                        navController = navController,
                        startDestination = Route.POKEMONWELCOME) {
                        composable(Route.POKEMONWELCOME){
                            WelcomeScreen(
                                onNavigateToLoader = { quantity ->
                                    navController.navigate(Route.POKEMONLOADER + "/$quantity")
                                }
                            )
                        }
                        composable(
                            route = Route.POKEMONLOADER + "/{quantity}",
                            arguments = listOf(navArgument("quantity",) { type = NavType.IntType})
                        ) {
                            val quantity = it.arguments?.getInt("quantity")!!
                            LoaderScreen(
                                quantity = quantity,
                                onNextClick = { navController.navigate(Route.POKEMONLIST) }
                            )
                        }
                        composable(Route.POKEMONLIST) {
                            ListScreen()
                        }
                        composable(Route.POKEMONDETAIL) {

                        }
                    }
                }
            }
        }
    }
}