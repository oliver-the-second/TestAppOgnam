package com.ilhomsoliev.testappognam.features.login.viewmodel

import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel
import com.ilhomsoliev.testappognam.data.repository.LoginRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class CodeViewModel(
    private val repository: LoginRepository,
): ViewModel() {

    private val _codeLength = MutableStateFlow(6)
    val codeLength = _codeLength.asStateFlow()
    
    private val _code = MutableStateFlow("")
    val code = _code.asStateFlow()

    private val _timer = MutableStateFlow(60)
    val timer = _timer.asStateFlow()
    
    private val _focuses = MutableStateFlow(lazy {
        val focuses = arrayListOf<FocusRequester>()
        repeat(codeLength.value) { focuses.add(FocusRequester()) }
        focuses
    }.value)
    val focuses = _focuses.asStateFlow()
    
    fun firstFocus() {
        focuses.value.first().requestFocus()
    }
    
    suspend fun onCodeClear() {
        _code.emit("")
        focuses.value[0].requestFocus()
    }
    
    suspend fun onCodeChange(index: Int, text: String) {
        if(code.value.length <= codeLength.value) {
            if(text.length == codeLength.value) {
                _code.emit(text)
            } else if(text.length < 2) {
                if(text == "") {
                    _code.emit(
                        code.value.substring(
                            0, code.value.lastIndex
                        )
                    )
                    if(index - 1 >= 0)
                        focuses.value[index - 1].requestFocus()
                } else {
                    _code.emit(code.value + text)
                    if(index + 1 < codeLength.value)
                        focuses.value[index + 1].requestFocus()
                }
            }
        } else _code.emit("")
    }
    
    suspend fun checkAuthCode(code: String) = withContext(IO){
        val response = repository.checkAuthCode(code)
        response
    }

    suspend fun onTimerChange() {
        _timer.emit(timer.value - 1)
    }
}