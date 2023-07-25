package com.dojo.pokedex_presentation.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun WelcomeScreen(
    onNavigateToLoader: (Int) -> Unit,
    viewModel: WelcomeViewLoader = hiltViewModel()
) {
    val quantity = viewModel.quantity

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pokemons to load",
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = MaterialTheme.colors.onSecondary,
                fontSize = 30.sp
            )
        )
        Row(
            modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center
        ) {
            
            PokeTextField(
                text = viewModel.quantity,
                onValueChange = viewModel::onQuantityEnter
            )
        }
        Button(
            onClick = {
                onNavigateToLoader(quantity.toInt())
            },
            enabled = viewModel.quantity.isNotEmpty()
        ) {
            Text(text ="Continue")
        }
    }
}

@Composable
fun PokeTextField(
    text: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colors.onSecondary,
        fontSize = 70.sp,
        textAlign = TextAlign.Center
    )
) {

    BasicTextField(
        textStyle = textStyle,
        value = text,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        enabled = true,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 64.dp) // margin left and right
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = Color(0xFFAAE9E6),
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
                contentAlignment = Alignment.Center

            ) {
                if (text.isEmpty()) {
                    Text(
                        text = "000",
                        fontSize = 70.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray,
                    )
                }
                innerTextField()
            }
        }
    )
}
