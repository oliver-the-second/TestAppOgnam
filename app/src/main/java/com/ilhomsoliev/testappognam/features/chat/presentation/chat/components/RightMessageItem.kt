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
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RightMessageItem(
    modifier: Modifier = Modifier,
    content: String,
    date: String,
    textSize: Int,
    textCorner: Int,
) {
    var isDropDownMessageActive by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Column(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        topStart = textCorner.dp,
                        topEnd = textCorner.dp,
                        bottomStart = textCorner.dp
                    )
                )
                .combinedClickable(
                    onClick = {
                    },
                    onLongClick = {
                        isDropDownMessageActive = true
                    },
                )
                .background(Color(0xFF51A9F0))
        ) {
            Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.End) {
                val customTextSelectionColors = TextSelectionColors(
                    handleColor = MaterialTheme.colors.primary,
                    backgroundColor = MaterialTheme.colors.background,
                )
                CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                    SelectionContainer {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Start),
                            color = Color.Black,
                            text = content,
                            fontSize = textSize.sp
                        )
                    }
                }

                Text(
                    text = date,
                    color = Color(0xFF2E5E85),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}