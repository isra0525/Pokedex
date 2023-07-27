package com.dojo.pokedex

import android.annotation.SuppressLint
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.dojo.core.navigation.Route
import com.dojo.pokedex.ui.theme.PokedexTheme
import com.dojo.pokedex_presentation.detail.DetailScreen
import com.dojo.pokedex_presentation.list.ListScreen
import com.dojo.pokedex_presentation.loader.LoaderScreen
import com.dojo.pokedex_presentation.welcome.WelcomeScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.startsWith
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PokedexEtoETest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private lateinit var navController: NavHostController

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Before
    fun setupAppNavHost() {
        composeRule.activity.setContent {
            //navController = TestNavHostController(LocalContext.current)
            //navController.navigatorProvider.addNavigator(ComposeNavigator())
            PokedexTheme {
                val scaffoldState = rememberScaffoldState()
                navController = rememberNavController()

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
                            arguments = listOf(navArgument("quantity") { type = NavType.IntType})
                        ) {
                            val quantity = it.arguments?.getInt("quantity")!!
                            LoaderScreen(
                                quantity = quantity,
                                onNextClick = { navController.navigate(Route.POKEMONLIST) }
                            )
                        }
                        composable(Route.POKEMONLIST) {
                            ListScreen(
                                OnNavigateToDetail = { pokemonId ->
                                    navController.navigate(Route.POKEMONDETAIL + "/$pokemonId")

                                }
                            )
                        }
                        composable(
                            route = Route.POKEMONDETAIL + "/{pokemonId}",
                            arguments = listOf(navArgument("pokemonId") {type = NavType.IntType})
                        ) {
                            val pokemonId = it.arguments?.getInt("pokemonId")!!
                            DetailScreen(
                                pokemonId = pokemonId
                            )
                        }
                    }
                }
            }
        }
    }

    @Test
    fun add_five_pokemons_and_show_them() {
        composeRule.onNodeWithText("Pokemons to load").assertIsDisplayed()
        val pokemonQuantity = 3

        composeRule
            .onNodeWithContentDescription("quantity")
            .performTextInput(pokemonQuantity.toString())

        composeRule.onNodeWithText("Continue").performClick()

        assertThat(
            "check pokemon loader destination",
            navController.currentDestination?.route,
            startsWith(Route.POKEMONLOADER)
        )

        composeRule.onNodeWithText("Loading...").assertIsDisplayed()
        composeRule.waitUntil(5000) { navController.currentDestination?.route == Route.POKEMONLIST }

        assertThat(
            "check pokemon list destination",
            navController.currentDestination?.route,
            startsWith(Route.POKEMONLIST)
        )


        composeRule
            .onNodeWithContentDescription("InputSearch")
            .assertIsDisplayed()

        composeRule
            .onNodeWithContentDescription("InputSearch")
            .performTextInput("bulbasaur")

        composeRule
            .onNodeWithContentDescription("Search")
            .performClick()
    }
}