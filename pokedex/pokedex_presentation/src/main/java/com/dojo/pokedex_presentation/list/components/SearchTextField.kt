package com.dojo.pokedex_presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(
    text: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "Search",
    shouldShowHint: Boolean = true,
    onFocusChanged: (FocusState) -> Unit
) {
    Box {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                    defaultKeyboardAction(ImeAction.Search)
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            modifier = modifier
                .clip(RoundedCornerShape(5.dp))
                .padding(2.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(5.dp)
                )
                .background(MaterialTheme.colors.surface)
                .fillMaxWidth()
                .padding(16.dp)
                .padding(end = 16.dp)
                .onFocusChanged { onFocusChanged(it) }
        )
        if (shouldShowHint) {
            Text(
                text = hint,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Light,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
            )
        }
        IconButton(
            onClick = onSearch,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .semantics { contentDescription = "Search" }
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search"
            )
        }
    }
}