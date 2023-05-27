package com.ilhomsoliev.testappognam.features.chat.presentation.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.testappognam.shared.components.ImageBox

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    date: String,
    imageUrl:String,
    onClick: () -> Unit,
) {
    var isDropDownMessageActive by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    onClick()
                },
                onLongClick = {
                    isDropDownMessageActive = true
                },
            )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ImageBox(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape), imageHttp = imageUrl
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column() {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = date, fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                    Text(
                        text = description,
                        fontSize = 16.sp,
                        color = Color(0xFF474747),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}