package com.ilhomsoliev.testappognam.features.login.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Arrangement.Top
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.testappognam.features.login.presentation.login.components.PhoneField
import com.ilhomsoliev.testappognam.shared.country.Country

interface LoginCallback {
    fun onNext()
    fun onPhoneChange(text: String)
    fun onClear()
    fun changeCountry()
}

@Immutable
data class LoginState(
    val phone: String,
    val isNextActive: Boolean,
    val country: Country,
)

@Composable
fun LoginContent(
    state: LoginState,
    modifier: Modifier = Modifier,
    callback: LoginCallback? = null,
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorScheme.background),
        SpaceBetween
    ) {
        Column(
            Modifier.weight(1f),
            Top, CenterHorizontally,
        ) {
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 36.dp)
            ) {

                Text(text = "Login", fontSize = 24.sp, color = Color.Black)

                Spacer(modifier = Modifier.padding(12.dp))

                PhoneField(
                    Modifier, state.phone,
                    state.country, callback
                )
                Spacer(modifier = Modifier.padding(12.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        callback?.onNext()
                    }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (state.isNextActive) Color(0xFFE90202)
                        else Color(0xFFD56464)
                    )
                ) {
                    Text(modifier = Modifier.padding(12.dp), text = "Next", fontSize = 26.sp)
                }
            }
        }
    }
}