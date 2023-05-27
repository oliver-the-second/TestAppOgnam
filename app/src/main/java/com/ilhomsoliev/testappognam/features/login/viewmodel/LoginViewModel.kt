package com.ilhomsoliev.testappognam.features.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.testappognam.app.navigation._country
import com.ilhomsoliev.testappognam.app.navigation.country
import com.ilhomsoliev.testappognam.data.repository.LoginRepository
import com.ilhomsoliev.testappognam.shared.country.CountryManager
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val repository: LoginRepository,
    private val countryManager: CountryManager,
): ViewModel() {
    
    init {
        viewModelScope.launch {
            if(_country.value.code == "NONE")
                _country.emit(countryManager.defaultCountry)
            else clearPhone()
        }
    }

    private val _phone = MutableStateFlow(country.value.clearPhoneDial)
    val phone = _phone.asStateFlow()

    suspend fun changePhone(text: String) {
        country.value.let { country ->
            phone.value.let { phone ->
                val dial = country.clearPhoneDial
                val length = text.length

                val newPhone = if (
                    length >= phone.length &&
                    !text.startsWith(dial)
                ) try {
                    dial.substring(0, length)
                } catch (_: Exception) {
                    text
                }
                else text

                _phone.emit(newPhone)
            }
        }
    }

    suspend fun clearPhone() {
        _phone.emit("")
        _phone.emit(country.value.clearPhoneDial)
    }
    
    suspend fun sendCode(): String? = withContext(IO){
        repository.sendCode("+" + _phone.value.replace(" ", ""))
            .let { value ->
                if(value){

                    null
                }else "Some error"
            }
    }


}