package com.ilhomsoliev.testappognam.features.chat.presentation.chat_list

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ilhomsoliev.testappognam.app.navigation.Screens
import com.ilhomsoliev.testappognam.features.chat.viewmodel.ChatListViewModel

@Composable
fun ChatListScreen(
    vm:ChatListViewModel,
    navController: NavController
) {


    ChatListContent(state = ChatListState(), callback = object : ChatListCallback{


        override fun onProfileClick() {
            navController.navigate(Screens.Profile)
        }

        override fun openChat() {
            navController.navigate(Screens.Chat)
        }

    })
}