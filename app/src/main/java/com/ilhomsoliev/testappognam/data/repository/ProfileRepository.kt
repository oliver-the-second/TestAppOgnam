package com.ilhomsoliev.testappognam.data.repository

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
        val token = dataStoreManager.getToken() ?: return@withContext null
        try {
            api.getProfile(token)
        } catch (e: Exception) {
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
        val token = dataStoreManager.getToken() ?: return@withContext null
        try {
            api.updateProfile(
                token, UpdateProfileRequest(
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

    suspend fun refreshToken() = withContext(IO){
        val refreshToken = dataStoreManager.getRefreshToken()?:return@withContext
        try {
            val res = api.refreshToken(refreshToken)
            dataStoreManager.changeToken(res.access_token)
            dataStoreManager.changeToken(res.refresh_token)
            res
        }catch (e:Exception){
            null
        }
    }

}