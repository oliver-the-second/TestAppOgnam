package com.ilhomsoliev.testappognam.features.chat.presentation.chat.components.chatField

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun ChatInput(
    modifier: Modifier = Modifier,
    value: String,
    isLoading: Boolean,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.Bottom
    ) {

        Spacer(modifier = Modifier.width(12.dp))

        ChatTextField(
            modifier = modifier.weight(1f),
            input = value,
            empty = value == "",
            onValueChange = onValueChange
        )

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier
                .size(48.dp)
                .padding(6.dp))
        } else {

            CustomIconButton(
                modifier = Modifier.size(48.dp),
                icon = Icons.Filled.Send,
                tint = Color.Gray
            ) {
                onClick()
            }
        }
    }
}

@Composable
private fun ChatTextField(
    modifier: Modifier = Modifier,
    input: String,
    empty: Boolean,
    onValueChange: (String) -> Unit
) {

    Surface(
        modifier = modifier.background(Color.Transparent),
        color = MaterialTheme.colors.surface,
    ) {
        Row(
            modifier = Modifier
                .background(Color.White),
            verticalAlignment = Alignment.Bottom
        ) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = circleButtonSize),
                    contentAlignment = Alignment.CenterStart
                ) {

                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, bottom = 4.dp, start = 0.dp, end = 12.dp),
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                        ),
                        value = input,
                        onValueChange = onValueChange,
                        cursorBrush = SolidColor(Color.Blue),
                        decorationBox = { innerTextField ->
                            Spacer(modifier = Modifier.width(12.dp))
                            if (empty) {
                                Text(
                                    text = "Type here",
                                    fontSize = 18.sp
                                )
                            }
                            innerTextField()

                        }
                    )
                }
            }
        }
    }
}

val circleButtonSize = 44.dp
@Composable
fun CustomIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    tint: Color = MaterialTheme.colors.onBackground,
    onClick: () -> Unit,
) {
    IconButton(modifier = modifier,onClick = {
        onClick()
    }) {
        Icon(
            icon,
            null,
            tint = tint
        )
    }
}