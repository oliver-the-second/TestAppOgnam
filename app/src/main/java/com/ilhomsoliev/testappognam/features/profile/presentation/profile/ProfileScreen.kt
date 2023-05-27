package com.ilhomsoliev.testappognam.features.profile.presentation.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.ilhomsoliev.testappognam.app.navigation.Screens
import com.ilhomsoliev.testappognam.features.profile.viewmodel.ProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    vm: ProfileViewModel,
    navController: NavController,
) {

    val scope = rememberCoroutineScope()
    val name by vm.name.collectAsState()
    val status by vm.status.collectAsState()
    val username by vm.username.collectAsState()
    val phone by vm.phone.collectAsState()
    val instagram by vm.instagram.collectAsState()
    val city by vm.city.collectAsState()
    val birthday by vm.birthday.collectAsState()
    val last by vm.last.collectAsState()
    val vk by vm.vk.collectAsState()
    val isOnline by vm.isOnline.collectAsState()
    val isLoading by vm.isLoading.collectAsState()
    val avatarUrl by vm.avatarUrl.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        scope.launch { vm.getProfile() }
    })

    ProfileContent(
        state = ProfileState(
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
            avatarUrl = avatarUrl,
            status = status,
        ), callback = object : ProfileCallback {
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

            override fun onEditProfile() {
                navController.navigate(Screens.EditProfile)
            }
        })


}