package com.ilhomsoliev.testappognam.features.profile.presentation.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.testappognam.R
import com.ilhomsoliev.testappognam.shared.components.Loader


data class ProfileState(
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

interface ProfileCallback {
    fun onLogout()
    fun onBack()
    fun onEditProfile()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    state: ProfileState,
    callback: ProfileCallback
) {

    Scaffold(topBar = {
        TopAppBar(title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    callback.onBack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
                Text(text = "Profile", fontFamily = FontFamily(
                    Font(R.font.roboto_regular)))
            }
        }, actions = {
            IconButton(onClick = {
                callback.onEditProfile()
            }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
            IconButton(onClick = {
                callback.onLogout()
            }) {
                Icon(imageVector = Icons.Default.Logout, contentDescription = null)
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
            Spacer(modifier = Modifier.height(12.dp))
            ProfileProperty(label = "Phone", state.phone)
            Spacer(modifier = Modifier.height(12.dp))
            ProfileProperty(label = "Username", state.username)
            Spacer(modifier = Modifier.height(12.dp))
            ProfileProperty(label = "Name", state.name)
            Spacer(modifier = Modifier.height(12.dp))
            ProfileProperty(label = "City", state.city)
            Spacer(modifier = Modifier.height(12.dp))
            ProfileProperty(label = "Instagram", state.instagram)
            Spacer(modifier = Modifier.height(12.dp))
            ProfileProperty(label = "Vk", state.vk)
            Spacer(modifier = Modifier.height(12.dp))
            ProfileProperty(label = "Birthday", state.birthday)
            Spacer(modifier = Modifier.height(120.dp))
        }
    }

    AnimatedVisibility(visible = state.isLoading) {
        Loader()
    }

}

@Composable
fun ProfileProperty(
    label: String,
    value: String,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$label: $value", fontSize = 24.sp, color = Color.Black,
            fontFamily = FontFamily(Font(R.font.roboto_regular))
        )
    }

}