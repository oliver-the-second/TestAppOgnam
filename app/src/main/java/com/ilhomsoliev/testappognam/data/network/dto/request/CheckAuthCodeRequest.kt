package com.ilhomsoliev.testappognam.data.network.dto.request

data class CheckAuthCodeRequest(
    val phone: String,
    val code:String
)