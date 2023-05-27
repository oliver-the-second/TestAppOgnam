package com.ilhomsoliev.testappognam.features.profile.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
    val isLoading:Boolean
)

interface ProfileCallback {
    fun onLogout()
    fun onBack()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    state: ProfileState,
    callback: ProfileCallback
) {
    AnimatedVisibility(visible = state.isLoading) {
        Loader()
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Row(verticalAlignment = Alignment.CenterVertically){
                IconButton(onClick = {
                    callback.onBack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
                Text(text = "Profile")
            }
        }, actions = {
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
            TextField(modifier = Modifier.fillMaxWidth(), value = state.username, enabled = false, onValueChange = {}, label = {
                Text(text = "Username", color = Color.Gray)
            })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(modifier = Modifier.fillMaxWidth(), value = state.name, onValueChange = {},label = {
                Text(text = "Name", color = Color.Gray)
            })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(modifier = Modifier.fillMaxWidth(), value = state.last, onValueChange = {},label = {
                Text(text = "Lastname", color = Color.Gray)
            })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(modifier = Modifier.fillMaxWidth(), value = state.city, onValueChange = {},label = {
                Text(text = "City", color = Color.Gray)
            })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(modifier = Modifier.fillMaxWidth(), value = state.instagram, onValueChange = {},label = {
                Text(text = "Instagram", color = Color.Gray)
            })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(modifier = Modifier.fillMaxWidth(), value = state.vk, onValueChange = {},label = {
                Text(text = "Vk", color = Color.Gray)
            })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(modifier = Modifier.fillMaxWidth(), value = state.birthday, onValueChange = {},label = {
                Text(text = "Birthday", color = Color.Gray)
            })
            Spacer(modifier = Modifier.height(12.dp))

        }
    }

}