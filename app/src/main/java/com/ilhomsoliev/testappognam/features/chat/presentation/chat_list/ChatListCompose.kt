package com.ilhomsoliev.testappognam.features.chat.presentation.chat_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class ChatListState(
    val value:Boolean = false
)

interface ChatListCallback {

    fun onProfileClick()
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

        }
    }
}