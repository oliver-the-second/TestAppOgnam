package com.ilhomsoliev.testappognam.data.network

import com.ilhomsoliev.testappognam.data.network.dto.request.CheckAuthCodeRequest
import com.ilhomsoliev.testappognam.data.network.dto.request.RegisterRequest
import com.ilhomsoliev.testappognam.data.network.dto.request.SendAuthCodeRequest
import com.ilhomsoliev.testappognam.data.network.dto.request.updateProfile.UpdateProfileRequest
import com.ilhomsoliev.testappognam.data.network.dto.response.CheckAuthCodeResponse
import com.ilhomsoliev.testappognam.data.network.dto.response.RefreshTokenResponse
import com.ilhomsoliev.testappognam.data.network.dto.response.RegisterResponse
import com.ilhomsoliev.testappognam.data.network.dto.response.SendAuthCodeResponse
import com.ilhomsoliev.testappognam.data.network.dto.response.profile.ProfileResponse
import com.ilhomsoliev.testappognam.data.network.dto.response.updateProfileResponse.UpdateProfileResponse
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.Headers

interface ServerApi {

    @HTTP(method = "POST", path = "users/register/", hasBody = true)
    @Headers(
        "accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun register(
        @Body
        request: RegisterRequest
    ): RegisterResponse


    @HTTP(method = "POST", path = "users/send-auth-code/", hasBody = true)
    @Headers(
        "accept: application/json",
        "Content-Type: application/json",
    )
    suspend fun sendAuthCode(
        @Body
        request: SendAuthCodeRequest
    ): SendAuthCodeResponse

    @HTTP(method = "POST", path = "users/check-auth-code/", hasBody = true)
    @Headers(
        "accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun checkAuthCode(
        @Body
        request: CheckAuthCodeRequest
    ): CheckAuthCodeResponse

    @HTTP(method = "GET", path = "users/me/", hasBody = true)
    @Headers(
        "accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun getProfile(
        @Header("Authorization") token: String,
    ): ProfileResponse

    @HTTP(method = "PUT", path = "users/me/", hasBody = true)
    @Headers(
        "accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body
        request: UpdateProfileRequest
    ): UpdateProfileResponse

    @HTTP(method = "GET", path = "users/refresh-token/", hasBody = true)
    @Headers(
        "accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun refreshToken(
        @Body
        refresh_token: String
    ): RefreshTokenResponse

}