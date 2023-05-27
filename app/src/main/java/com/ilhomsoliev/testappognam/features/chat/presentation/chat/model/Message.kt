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
        content = "Hello",
        owner = "LEFT",
        dateCreated = System.currentTimeMillis(),
    ),
    Message(
        id = 2,
        content = "Hello",
        owner = "RIGHT",
        dateCreated = System.currentTimeMillis(),
    ),
    Message(
        id = 3,
        content = "I am doing my Test app for Mango FZCO",
        owner = "LEFT",
        dateCreated = System.currentTimeMillis(),
    ),
    Message(
        id = 4,
        content = "Good luck with that!",
        owner = "RIGHT",
        dateCreated = System.currentTimeMillis(),
    ),
)