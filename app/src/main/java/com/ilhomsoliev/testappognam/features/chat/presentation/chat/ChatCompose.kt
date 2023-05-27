package com.ilhomsoliev.testappognam.features.chat.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.testappognam.features.chat.presentation.chat.chatField.ChatInput
import com.ilhomsoliev.testappognam.shared.components.ImageBox

data class ChatState(
    val isLoading: Boolean = false,
    val imageUrl: String ,
    val chatName:String = "Chat name",
    val curMessage:String = "Chat name",
)

interface ChatCallback {

    fun onBack()
    fun onMessageChange(value:String)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatContent(
    state: ChatState,
    callback: ChatCallback
) {
    var isDropDownMenuActive by remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(modifier = Modifier
            .fillMaxWidth(),
            actions = {
                IconButton(onClick = {
                    isDropDownMenuActive = true
                }) {
                    Icon(Icons.Filled.MoreVert, null, tint = Color.White)
                    DropdownMenu(
                        modifier = Modifier.background(Color.White),
                        offset = DpOffset(0.dp, 0.dp),
                        expanded = isDropDownMenuActive,
                        onDismissRequest = { isDropDownMenuActive = false }
                    ) {
                        DropdownMenuItem(modifier = Modifier.fillMaxWidth(), onClick = {
                            isDropDownMenuActive = false
                        }) {
                            IconWithText(
                                icon = Icons.Default.Search,
                                "Search"
                            )
                        }

                        DropdownMenuItem(modifier = Modifier.fillMaxWidth(), onClick = {
                            isDropDownMenuActive = false
                        }) {
                            IconWithText(
                                icon = Icons.Default.Edit,
                                "Edit"
                            )
                        }
                        DropdownMenuItem(modifier = Modifier.fillMaxWidth(), onClick = {
                            isDropDownMenuActive = false
                        }) {
                            IconWithText(
                                icon = Icons.Default.ClearAll,
                                "Clear Chat"
                            )
                        }
                    }
                }
            }, backgroundColor = MaterialTheme.colors.primary, title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {
                        callback.onBack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                    ImageBox(
                        imageHttp = state.imageUrl,
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape),
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = state.chatName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp
                    )

                }
            })
    },bottomBar = {
        ChatInput(
            value = state.curMessage,
            isLoading = state.isLoading,
            onClick = {
/*                coroutineScope.launch {
                    scrollState.animateScrollToItem(if (state.messages.isNotEmpty()) state.messages.size - 1 else 0)
                }*/
            },
            onValueChange = {
                callback.onMessageChange(it)
            }
        )
    }) {
        Column(modifier = Modifier.fillMaxSize().padding(it)) {

        }

    }

}

@Composable
fun IconWithText(
    icon: ImageVector,
    text: String,
) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .size(22.dp)
                .padding(horizontal = 0.dp),
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = text,
            maxLines = 1,
            softWrap = true,
            color = Color.Black
        )
    }

}