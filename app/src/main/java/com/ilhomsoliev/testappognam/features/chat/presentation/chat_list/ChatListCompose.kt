package com.ilhomsoliev.testappognam.features.chat.presentation.chat_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ilhomsoliev.testappognam.features.chat.presentation.components.ChatItem

data class ChatListState(
    val value:Boolean = false
)

interface ChatListCallback {

    fun onProfileClick()
    fun openChat()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListContent(
    state: ChatListState,
    callback: ChatListCallback
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Chat List")
        }, actions = {
            IconButton(onClick = {
                callback.onProfileClick()
            }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)
            }
        })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Divider()
            LazyColumn(){ 
                items(10) {
                    ChatItem(
                        title = "Chat title",
                        description = "Description",
                        date = "1 day ago",
                        imageUrl = "https://media.npr.org/assets/img/2020/02/27/wide-use_hpromophoto_helenepambrun-72fdb64792139d94a06f18686d0bb3131a238a70-s1100-c50.jpg"
                    ) {
                        callback.openChat()
                    }
                    Divider()
                }
            }

        }
    }
}