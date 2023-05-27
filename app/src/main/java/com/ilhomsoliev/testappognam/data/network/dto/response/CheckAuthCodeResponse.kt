package com.ilhomsoliev.testappognam.data.network.dto.response

data class CheckAuthCodeResponse(
    val access_token: String,
    val is_user_exists: Boolean,
    val refresh_token: String,
    val user_id: Int
)