package com.ilhomsoliev.testappognam.data.repository

import android.util.Log
import com.ilhomsoliev.testappognam.data.local.DataStoreManager
import com.ilhomsoliev.testappognam.data.network.ServerApi
import com.ilhomsoliev.testappognam.data.network.dto.request.CheckAuthCodeRequest
import com.ilhomsoliev.testappognam.data.network.dto.request.RegisterRequest
import com.ilhomsoliev.testappognam.data.network.dto.request.SendAuthCodeRequest
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class LoginRepository(
    private val api: ServerApi,
    private val dataStoreManager: DataStoreManager,
) {

    suspend fun sendCode(phone: String) = withContext(IO) {
        dataStoreManager.changePhone(phone)
        try {
            api.sendAuthCode(SendAuthCodeRequest(phone)).is_success
        } catch (e: Exception) {
            Log.d("Login Exception", "${e.message}")
            false
        }
    }

    suspend fun checkAuthCode(code: String) = withContext(IO) {
        val phone = dataStoreManager.getPhone()
        if (phone.isEmpty()) return@withContext null
        try {
            val response = api.checkAuthCode(CheckAuthCodeRequest(phone, code))
            dataStoreManager.changeToken(response.access_token)
            dataStoreManager.changeRefreshToken(response.refresh_token)
            response.is_user_exists
        } catch (e: Exception) {
            Log.d("Login Exception", "${e.message}")
            null
        }
    }

    suspend fun register(name: String, username: String) = withContext(IO) {
        val phone = dataStoreManager.getPhone()
        if (phone.isEmpty()) return@withContext null
        try {
            val response = api.register(RegisterRequest(name, phone, username))
            dataStoreManager.changeToken(response.access_token)
            dataStoreManager.changeRefreshToken(response.refresh_token)
            response.access_token to response.refresh_token
        } catch (e: Exception) {
            Log.d("Login Exception", "${e.message}")
            null
        }
    }

}