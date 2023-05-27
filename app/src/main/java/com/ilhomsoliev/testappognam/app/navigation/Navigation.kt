package com.ilhomsoliev.testappognam.app.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.ilhomsoliev.testappognam.data.local.DataStoreManager
import com.ilhomsoliev.testappognam.features.chat.presentation.chat.ChatScreen
import com.ilhomsoliev.testappognam.features.chat.presentation.chat_list.ChatListScreen
import com.ilhomsoliev.testappognam.features.chat.viewmodel.ChatListViewModel
import com.ilhomsoliev.testappognam.features.chat.viewmodel.ChatViewModel
import com.ilhomsoliev.testappognam.features.login.presentation.auth_profile.AuthProfileScreen
import com.ilhomsoliev.testappognam.features.login.presentation.login.LoginScreen
import com.ilhomsoliev.testappognam.features.login.presentation.login.country.CountryBs
import com.ilhomsoliev.testappognam.features.login.viewmodel.CodeViewModel
import com.ilhomsoliev.testappognam.features.login.viewmodel.CountryBsViewModel
import com.ilhomsoliev.testappognam.features.login.viewmodel.LoginViewModel
import com.ilhomsoliev.testappognam.shared.country.Country
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.androidx.compose.koinViewModel
import com.ilhomsoliev.testappognam.features.login.presentation.code.CodeScreen
import com.ilhomsoliev.testappognam.features.login.viewmodel.AuthProfileViewModel
import com.ilhomsoliev.testappognam.features.profile.presentation.edit_profile.EditProfileScreen
import com.ilhomsoliev.testappognam.features.profile.presentation.profile.ProfileScreen
import com.ilhomsoliev.testappognam.features.profile.viewmodel.EditProfileViewModel
import com.ilhomsoliev.testappognam.features.profile.viewmodel.ProfileViewModel
import org.koin.mp.KoinPlatformTools

val _country = MutableStateFlow(Country())
val country = _country.asStateFlow()

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalMaterialApi::class)
@Composable
fun Navigation() {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val bottomSheetNavigator = remember{
        BottomSheetNavigator(sheetState)
    }
    val navController = rememberNavController(bottomSheetNavigator)
    val dataStore by lazy { KoinPlatformTools.defaultContext().get().get<DataStoreManager>()  }

    LaunchedEffect(key1 = Unit, block = {
        if(dataStore.getToken().isNotEmpty()){
            navController.navigate(Screens.ChatList)
        }
    })

    ModalBottomSheetLayout(bottomSheetNavigator) {
        NavHost(navController = navController, startDestination = Screens.Login) {
            composable(route = Screens.Login) {
                val viewModel = koinViewModel<LoginViewModel>()
                LoginScreen(viewModel, navController)
            }
            bottomSheet(route = Screens.SelectCountry) {
                val viewModel = koinViewModel<CountryBsViewModel>()

                CountryBs(viewModel, navController)
            }
            composable(Screens.Code) {
                val viewModel = koinViewModel<CodeViewModel>()
                CodeScreen(vm = viewModel, navController)
            }
            composable(Screens.AuthProfile) {
                val viewModel = koinViewModel<AuthProfileViewModel>()
                AuthProfileScreen(vm = viewModel, navController)
            }
            composable(Screens.ChatList){
                val viewModel = koinViewModel<ChatListViewModel>()
                ChatListScreen(vm = viewModel, navController)
            }
            composable(Screens.Chat){
                val viewModel = koinViewModel<ChatViewModel>()
                ChatScreen(vm = viewModel, navController)
            }
            composable(Screens.Profile){
                val viewModel = koinViewModel<ProfileViewModel>()
                ProfileScreen(vm = viewModel, navController = navController)
            }
            composable(Screens.EditProfile){
                val viewModel = koinViewModel<EditProfileViewModel>()
                EditProfileScreen(vm = viewModel, navController = navController)
            }
        }
    }
}