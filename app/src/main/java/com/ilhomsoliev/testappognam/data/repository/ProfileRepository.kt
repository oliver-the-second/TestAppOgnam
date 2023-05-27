package com.ilhomsoliev.testappognam.data.repository

import android.util.Log
import com.ilhomsoliev.testappognam.data.local.DataStoreManager
import com.ilhomsoliev.testappognam.data.network.ServerApi
import com.ilhomsoliev.testappognam.data.network.dto.request.updateProfile.Avatar
import com.ilhomsoliev.testappognam.data.network.dto.request.updateProfile.UpdateProfileRequest
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class ProfileRepository(
    private val api: ServerApi,
    private val dataStoreManager: DataStoreManager,
) {
    suspend fun getProfile() = withContext(IO) {
        refreshToken()
        val token = dataStoreManager.getToken()
        Log.d("Hello", "Token: $token")
        if (token.isEmpty()) return@withContext null
        try {
            val res = api.getProfile("Bearer $token")
            Log.d("Hello", res.profile_data.phone)
            res
        } catch (e: Exception) {
            Log.d("Hello", e.message.toString())
            null
        }
    }

    suspend fun updateProfile(
        avatar: Avatar,
        birthday: String,
        city: String,
        instagram: String,
        name: String,
        status: String,
        username: String,
        vk: String
    ) = withContext(IO) {
        refreshToken()
        val token = dataStoreManager.getToken()
        if (token.isEmpty())return@withContext null
        try {
            api.updateProfile(
                "Bearer $token", UpdateProfileRequest(
                    avatar = avatar,
                    birthday = birthday,
                    city = city,
                    instagram = instagram,
                    name = name,
                    status = status,
                    username = username,
                    vk = vk,
                )
            )
        } catch (e: Exception) {
            null
        }
    }

    suspend fun refreshToken() = withContext(IO) {
        val refreshToken = dataStoreManager.getRefreshToken()
        if(refreshToken.isEmpty())return@withContext
        try {
            val res = api.refreshToken(refreshToken)
            dataStoreManager.changeToken(res.access_token)
            dataStoreManager.changeToken(res.refresh_token)
            res
        } catch (e: Exception) {
            null
        }
    }

    suspend fun logout() = withContext(IO) {
        dataStoreManager.changeToken("")
        dataStoreManager.changePhone("")
        dataStoreManager.changeRefreshToken("")
    }

}