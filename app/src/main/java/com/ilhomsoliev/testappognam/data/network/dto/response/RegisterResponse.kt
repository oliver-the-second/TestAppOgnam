package com.ilhomsoliev.testappognam.data.network.dto.response

data class RegisterResponse(
    val access_token: String,
    val refresh_token: String,
    val user_id: Int
)