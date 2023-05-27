package com.ilhomsoliev.testappognam.features.profile.viewmodel

import androidx.lifecycle.ViewModel
import com.ilhomsoliev.testappognam.data.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel(
    private val repository: ProfileRepository,
) : ViewModel() {
    private val _phone = MutableStateFlow("")
    val phone = _phone.asStateFlow()

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()
    private val _last = MutableStateFlow("")
    val last = _last.asStateFlow()

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _vk = MutableStateFlow("")
    val vk = _vk.asStateFlow()

    private val _instagram = MutableStateFlow("")
    val instagram = _instagram.asStateFlow()

    private val _isOnline = MutableStateFlow(false)
    val isOnline = _isOnline.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _city = MutableStateFlow("")
    val city = _city.asStateFlow()

    private val _birthday = MutableStateFlow("")
    val birthday = _birthday.asStateFlow()

    private val _status = MutableStateFlow("")
    val status = _status.asStateFlow()


    private val _avatarUrl = MutableStateFlow<String?>(null)
    val avatarUrl = _avatarUrl.asStateFlow()


    suspend fun getProfile() {
        _isLoading.emit(true)
        val res = repository.getProfile()?.profile_data
        if (res == null)
            _isLoading.emit(false)
        res?.let { profile ->
            _isLoading.emit(false)
            _phone.emit(profile.phone)
            _name.emit(profile.name)
            _username.emit(profile.username)
            _last.emit(profile.last ?: "")
            _instagram.emit(profile.instagram ?: "")
            _vk.emit(profile.vk ?: "")
            _city.emit(profile.city ?: "")
            _birthday.emit(profile.birthday ?: "2000-01-30")
            _avatarUrl.emit(profile.avatars?.bigAvatar)
        }
    }


    suspend fun logout() {
        repository.logout()
    }

}