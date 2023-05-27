package com.ilhomsoliev.testappognam.features.chat.presentation.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.ilhomsoliev.testappognam.features.chat.viewmodel.ChatViewModel
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(
    vm:ChatViewModel,
    navController: NavController,
) {
    val scope = rememberCoroutineScope()

    val curMessage by vm.curMessage.collectAsState()

    ChatContent(
        state = ChatState(
            isLoading = false,
            imageUrl = "https://media.npr.org/assets/img/2020/02/27/wide-use_hpromophoto_helenepambrun-72fdb64792139d94a06f18686d0bb3131a238a70-s1100-c50.jpg",
            chatName = "Talking with...",
            curMessage = curMessage,
        ), callback = object : ChatCallback{
            override fun onBack() {
                navController.popBackStack()
            }

            override fun onMessageChange(value: String) {
                scope.launch{
                    vm.changeMessage(value)
                }
            }

        }
    )
}