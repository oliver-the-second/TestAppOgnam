package com.ilhomsoliev.testappognam.features.chat.presentation.chat.model

data class Message(
    val id: Int? = null,
    val content: String,
    val owner: String,
    val dateCreated: Long,
)
var counter = 5
val DemoMessages = listOf<Message>(
    Message(
        id = 1,
        content = "Привет",
        owner = "LEFT",
        dateCreated = System.currentTimeMillis(),
    ),
    Message(
        id = 2,
        content = "Привет",
        owner = "RIGHT",
        dateCreated = System.currentTimeMillis(),
    ),
    Message(
        id = 3,
        content = "Я делаю тестовое приложение для Mango FZCO.",
        owner = "LEFT",
        dateCreated = System.currentTimeMillis(),
    ),
    Message(
        id = 4,
        content = "Удачи с этим!",
        owner = "RIGHT",
        dateCreated = System.currentTimeMillis(),
    ),
)