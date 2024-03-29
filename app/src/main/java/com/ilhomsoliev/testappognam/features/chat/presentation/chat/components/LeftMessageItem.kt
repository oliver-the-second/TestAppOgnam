package com.ilhomsoliev.testappognam.features.chat.presentation.chat.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.testappognam.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LeftMessageItem(
    modifier: Modifier = Modifier,
    content: String,
    date: String,
    textSize: Int,
    textCorner: Int,
) {
    var isDropDownMessageActive by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
        Column(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        topStart = textCorner.dp,
                        topEnd = textCorner.dp,
                        bottomEnd = textCorner.dp
                    )
                )
                .combinedClickable(
                    onClick = {
                        isDropDownMessageActive = true
                    },
                    onLongClick = {
                        isDropDownMessageActive = true

                    },
                )
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                val customTextSelectionColors = TextSelectionColors(
                    handleColor = MaterialTheme.colors.primary,
                    backgroundColor = MaterialTheme.colors.background,
                )
                CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                    SelectionContainer {
                        Text(text = content, color = Color.Black, fontSize = textSize.sp, fontFamily = FontFamily(
                            Font(R.font.roboto_regular)
                        )
                        )
                    }
                }
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = date,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}