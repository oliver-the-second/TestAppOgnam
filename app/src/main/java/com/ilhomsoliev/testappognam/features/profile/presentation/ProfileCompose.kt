package com.ilhomsoliev.testappognam.features.profile.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


data class ProfileState(
    val value:Boolean = false
)

interface ProfileCallback {

    fun onProfileClick()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    state: ProfileState,
    callback: ProfileCallback
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Profile ")
        }, actions = {
            IconButton(onClick = {
                callback.onProfileClick()
            }) {
                Icon(imageVector = Icons.Default.Logout, contentDescription = null)
            }
        })
    }) {
        Column(modifier = Modifier.fillMaxSize().padding(it)) {

        }
    }

}