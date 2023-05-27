package com.ilhomsoliev.testappognam.features.login.presentation.auth_profile

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ilhomsoliev.testappognam.app.navigation.Screens
import com.ilhomsoliev.testappognam.features.login.viewmodel.AuthProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun AuthProfileScreen(
    vm: AuthProfileViewModel,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val name by vm.name.collectAsState()
    val username by vm.username.collectAsState()
    val phone by vm.phone.collectAsState()
    val isNextActive = remember(username, name) {
        name.isNotEmpty() && username.isNotEmpty()
    }

    AuthProfileContent(state = AuthProfileState(phone, name, username, isNextActive), callback = object : AuthProfileCallback{
        override fun onNameChange(value: String) {
            scope.launch { vm.changeName(value) }
        }

        override fun onUsernameChange(value: String) {
            scope.launch { vm.changeUsername(value) }
        }

        override fun onNext() {
            scope.launch {
                vm.register().let { value->
                    if(value == null){
                        navController.navigate(Screens.ChatList)
                    }else
                        Toast.makeText(context, "Какая-то ошибка", Toast.LENGTH_LONG).show()
                }
            }
        }
    })
}