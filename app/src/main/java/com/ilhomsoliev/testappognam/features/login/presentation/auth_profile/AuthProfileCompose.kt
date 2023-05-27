package com.ilhomsoliev.testappognam.features.login.presentation.auth_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class AuthProfileState(
    val phone: String,
    val name: String,
    val username: String,
    val isNextActive: Boolean,
)

interface AuthProfileCallback {
    fun onNameChange(value: String)
    fun onUsernameChange(value: String)
    fun onNext()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthProfileContent(
    state: AuthProfileState,
    modifier: Modifier = Modifier,
    callback: AuthProfileCallback? = null,
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background),
        Arrangement.SpaceBetween
    ) {
        Column(
            Modifier.weight(1f),
            Arrangement.Top, Alignment.CenterHorizontally,
        ) {
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 36.dp)
            ) {
                Text(text = "New User", fontSize = 24.sp, color = Color.Black)

                Spacer(modifier = Modifier.padding(12.dp))
                TextField(modifier = Modifier.fillMaxWidth(), value = state.phone, enabled = false, onValueChange = {}, label = {
                    Text(text = "Phone")
                })
                Spacer(modifier = Modifier.padding(12.dp))
                TextField(modifier = Modifier.fillMaxWidth(), value = state.name, onValueChange = { callback?.onNameChange(it) },
                    label = {
                    Text(text = "Name",color = Color.Gray)
                })
                Spacer(modifier = Modifier.padding(12.dp))
                TextField(modifier = Modifier.fillMaxWidth(), value = state.username, onValueChange = { callback?.onUsernameChange(it) }, label = {
                    Text(text = "Username", color = Color.Gray)
                })
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
