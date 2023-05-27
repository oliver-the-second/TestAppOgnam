package com.ilhomsoliev.testappognam.features.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.testappognam.data.local.DataStoreManager
import com.ilhomsoliev.testappognam.data.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthProfileViewModel(
    private val repository: LoginRepository,
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _phone = MutableStateFlow("")
    val phone = _phone.asStateFlow()

    init {
        viewModelScope.launch {
            _phone.emit(dataStoreManager.getPhone()?:"")
        }
    }

    suspend fun changeName(value: String) {
        _name.emit(value)
    }

    suspend fun changeUsername(value: String) {
        _username.emit(value)
    }

    suspend fun register() = withContext(Dispatchers.IO) {
        repository.register(_name.value, _username.value)
            .let { value ->
                if (value == null) {
                    "Some error on register"
                } else {
                    null
                }
            }
    }
}