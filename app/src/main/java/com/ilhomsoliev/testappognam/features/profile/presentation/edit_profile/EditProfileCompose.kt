package com.ilhomsoliev.testappognam.features.profile.presentation.edit_profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.testappognam.shared.components.Loader


data class EditProfileState(
    val name: String,
    val username: String,
    val vk: String,
    val instagram: String,
    val phone: String,
    val last: String,
    val isOnline: Boolean,
    val city: String,
    val birthday: String,
    val isLoading: Boolean
)

interface EditProfileCallback {
    fun onLogout()
    fun onBack()
    fun onSave()
    fun onNameChange(value: String)
    fun onLastChange(value: String)
    fun onVkChange(value: String)
    fun onInstagramChange(value: String)
    fun onCityChange(value: String)
    fun onBirthdayChange(value: String)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileContent(
    state: EditProfileState,
    callback: EditProfileCallback
) {
    AnimatedVisibility(visible = state.isLoading) {
        Loader()
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    callback.onBack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
                Text(text = "Edit Profile")
            }
        })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 12.dp)
                .padding(it)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.phone,
                onValueChange = {},
                enabled = false,
                label = {
                    Text(text = "Phone", color = Color.Gray)
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.username,
                enabled = false,
                onValueChange = {},
                label = {
                    Text(text = "Username", color = Color.Gray)
                })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(modifier = Modifier.fillMaxWidth(), value = state.name, onValueChange = {
                callback.onNameChange(it)
            }, label = {
                Text(text = "Name", color = Color.Gray)
            })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.city,
                onValueChange = {
                    callback.onCityChange(it)
                },
                label = {
                    Text(text = "City", color = Color.Gray)
                })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.instagram,
                onValueChange = {
                    callback.onInstagramChange(it)

                },
                label = {
                    Text(text = "Instagram", color = Color.Gray)
                })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.vk,
                onValueChange = {
                    callback.onVkChange(it)
                },
                label = {
                    Text(text = "Vk", color = Color.Gray)
                })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.birthday,
                onValueChange = {
                    callback.onBirthdayChange(it)
                },
                label = {
                    Text(text = "Birthday", color = Color.Gray)
                })
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    callback.onSave()
                }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFE90202)
                )
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = "Save",
                    fontSize = 26.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(120.dp))
        }
    }

}