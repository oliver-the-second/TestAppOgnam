package com.ilhomsoliev.testappognam.features.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.testappognam.data.network.dto.request.updateProfile.Avatar
import com.ilhomsoliev.testappognam.data.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditProfileViewModel(
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

    private val _avatar = MutableStateFlow("" to "")
    val avatar = _avatar.asStateFlow()

    private val _avatarUrl = MutableStateFlow<String?>(null)
    val avatarUrl = _avatarUrl.asStateFlow()


    init {
        viewModelScope.launch {
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
    }

    suspend fun logout() {
        repository.logout()
    }

    suspend fun saveData() = withContext(IO) {
        _isLoading.emit(true)
        val res = repository.updateProfile(
            avatar = if (_avatar.value.first.isNotEmpty() && _avatar.value.second.isNotEmpty()) Avatar(
                _avatar.value.second,
                _avatar.value.first
            ) else null,
            birthday = _birthday.value,
            city = _city.value,
            instagram = _instagram.value,
            name = _name.value,
            status = _status.value,
            username = _username.value,
            vk = _vk.value,
        )
        _isLoading.emit(false)
    }

    suspend fun onNameChange(value: String) {
        _name.emit(value)
    }

    suspend fun onStatusChange(value: String) {
        _status.emit(value)
    }

    suspend fun onLastChange(value: String) {
        _last.emit(value)
    }

    suspend fun onVkChange(value: String) {
        _vk.emit(value)
    }

    suspend fun onInstagramChange(value: String) {
        _instagram.emit(value)
    }

    suspend fun onBirthdayChange(value: String) {
        _birthday.emit(value)
    }

    suspend fun onCityChange(value: String) {
        _city.emit(value)
    }

    suspend fun onAvatarChange(value: Pair<String, String>) {
        _avatar.emit(value)
    }
}