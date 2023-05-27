package com.ilhomsoliev.testappognam.features.chat.viewmodel

import androidx.lifecycle.ViewModel
import com.ilhomsoliev.testappognam.features.chat.presentation.chat.model.DemoMessages
import com.ilhomsoliev.testappognam.features.chat.presentation.chat.model.Message
import com.ilhomsoliev.testappognam.features.chat.presentation.chat.model.counter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel : ViewModel() {

    private val _curMessage = MutableStateFlow("")
    val curMessage = _curMessage.asStateFlow()

    private val _messages = MutableStateFlow(DemoMessages)
    val messages = _messages.asStateFlow()

    suspend fun changeMessage(value: String) {
        _curMessage.emit(value)
    }

    suspend fun sendMessage() {
        if (_curMessage.value.isEmpty()) return
        val list = _messages.value.toMutableList()
        list.add(
            Message(
                id = counter++,
                content = _curMessage.value,
                owner = "RIGHT",
                dateCreated = System.currentTimeMillis()
            )
        )
        _messages.emit(list)
        _curMessage.emit("")

    }
}