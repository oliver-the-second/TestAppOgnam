package com.ilhomsoliev.testappognam.features.profile.presentation.edit_profile

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ilhomsoliev.testappognam.app.navigation.Screens
import com.ilhomsoliev.testappognam.features.profile.viewmodel.EditProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(
    vm: EditProfileViewModel,
    navController: NavController,
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val name by vm.name.collectAsState()
    val username by vm.username.collectAsState()
    val phone by vm.phone.collectAsState()
    val instagram by vm.instagram.collectAsState()
    val city by vm.city.collectAsState()
    val birthday by vm.birthday.collectAsState()
    val last by vm.last.collectAsState()
    val vk by vm.vk.collectAsState()
    val isOnline by vm.isOnline.collectAsState()
    val isLoading by vm.isLoading.collectAsState()
    val avatar by vm.avatar.collectAsState()
    val avatarUrl by vm.avatarUrl.collectAsState()


    EditProfileContent(
        state = EditProfileState(
            name = name,
            username = username,
            vk = vk,
            instagram = instagram,
            phone = phone,
            last = last,
            isOnline = isOnline,
            city = city,
            birthday = birthday,
            isLoading = isLoading,
            avatar = avatar,
            avatarUrl = avatarUrl,
        ), callback = object : EditProfileCallback {
            override fun onLogout() {
                scope.launch {
                    vm.logout()
                    navController.navigate(Screens.Login) {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                }
            }

            override fun onBack() {
                navController.popBackStack()
            }

            override fun onSave() {
                scope.launch {
                    val res = vm.saveData()
                    res?.let {
                        Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                        navController.popBackStack()
                    }
                    if (res == null) {
                        Toast.makeText(context, "Fail", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onNameChange(value: String) {
                scope.launch { vm.onNameChange(value) }
            }

            override fun onLastChange(value: String) {
                scope.launch { vm.onLastChange(value) }
            }

            override fun onVkChange(value: String) {
                scope.launch { vm.onVkChange(value) }
            }

            override fun onInstagramChange(value: String) {
                scope.launch { vm.onInstagramChange(value) }
            }

            override fun onCityChange(value: String) {
                scope.launch { vm.onCityChange(value) }
            }

            override fun onBirthdayChange(value: String) {
                scope.launch { vm.onBirthdayChange(value) }
            }

            override fun onAvatarChange(value: Pair<String, String>) {
                scope.launch { vm.onAvatarChange(value) }
            }
        })
}