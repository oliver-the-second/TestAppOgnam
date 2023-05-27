package com.ilhomsoliev.testappognam.features.chat.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel : ViewModel() {

    private val _curMessage = MutableStateFlow("")
    val curMessage = _curMessage.asStateFlow()

    suspend fun changeMessage(value:String) {
        _curMessage.emit(value)
    }
}