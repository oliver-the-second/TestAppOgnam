package com.ilhomsoliev.testappognam.features

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.testappognam.R
import com.ilhomsoliev.testappognam.features.login.presentation.login.components.TSearchBar

@Composable
fun TDivider(
    modifier: Modifier = Modifier,
    color: Color = if(isSystemInDarkTheme())
        Color(0xFFDFDFDF)
    else Color(0xFF464649),
) {
    Divider(modifier, 1.dp, color)
}
@Composable
fun lazyItemsShapes(
    index: Int, size: Int,
    radius: Dp = 12.dp,
) = if(size == 1)
    RoundedCornerShape(radius)
else when(index) {
    0 -> RoundedCornerShape(
        topStart = radius,
        topEnd = radius
    )

    size - 1 -> RoundedCornerShape(
        bottomStart = radius,
        bottomEnd = radius
    )

    else -> RoundedCornerShape(0.dp)
}

/*@Composable
@Suppress("unused")
fun lazyRowItemsShapes(
    index: Int, size: Int,
) = if(size == 1)
    MaterialTheme.shapes.medium
else when(index) {
    0 -> shapes.extraLargeStartRoundedShape
    size - 1 -> shapes.extraLargeEndRoundedShape
    else -> shapes.zero}*/

/*
@Composable
fun lazyRowAlbumItemsShapes(
    index: Int, size: Int,
) = if(size == 1)
    RoundedCornerShape(16.0.dp)
else when(index) {
    0 -> RoundedCornerShape(topStart = 16.0.dp, bottomStart = 16.0.dp)
    size - 1 -> RoundedCornerShape(topEnd = 16.0.dp, bottomEnd = 16.0.dp)
    else -> RoundedCornerShape(0.dp)
}
*/

@Composable
fun SearchActionBar(
    state: SearchState,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxWidth()) {
        AnimatedVisibility(
            !state.state,
            Modifier.height(60.dp),
            fadeIn(), fadeOut()
        ) { LabelBar(Modifier, state) }
        AnimatedVisibility(
            state.state,
            Modifier.height(60.dp),
            slideInHorizontally { it / 2 } + fadeIn(),
            slideOutHorizontally { it / 2 } + fadeOut()
        ) { SearchBar(Modifier, state) }
    }
}

@Composable
private fun LabelBar(
    modifier: Modifier = Modifier,
    state: SearchState,
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically
    ) {
        Text(
            state.name ?: "", style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.tertiary, fontFamily = FontFamily(
                Font(R.font.roboto_regular))
        )
        IconButton({
            state.state = true
            state.onExpandSearch?.let { it(true) }
        }) {
            Icon(
                painterResource(R.drawable.magnifier),
                (state.placeHolder
                    ?: ""),
                Modifier.size(22.dp),
                MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

data class SearchState(
    val name: String? = null,
    var state: Boolean,
    val text: String,
    val onChangeText: (it: String) -> Unit,
    val online: Boolean = false,
    val placeHolder: String? = null,
    val onExpandSearch: ((Boolean) -> Unit)? = null,
)


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    state: SearchState,
) {
    TSearchBar(
        value = state.text,
        modifier = modifier,
        label = state.placeHolder
            ?: "Поиск",
        isOnline = state.online,
        fullWidth = false,
        onBack = {
            state.state = false
            state.onExpandSearch?.let { it(false) }
        },
        onTextChange = { state.onChangeText(it) },
        errorActive = false
    )
}