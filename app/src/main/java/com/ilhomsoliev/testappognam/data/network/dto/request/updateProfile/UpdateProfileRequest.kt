package com.ilhomsoliev.testappognam.data.network.dto.request.updateProfile

data class UpdateProfileRequest(
    val avatar: Avatar,
    val birthday: String,
    val city: String,
    val instagram: String,
    val name: String,
    val status: String,
    val username: String,
    val vk: String
)