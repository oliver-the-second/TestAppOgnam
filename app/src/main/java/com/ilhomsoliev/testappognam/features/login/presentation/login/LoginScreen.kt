package com.ilhomsoliev.testappognam.features.login.presentation.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ilhomsoliev.testappognam.app.navigation.Screens
import com.ilhomsoliev.testappognam.app.navigation.country
import kotlinx.coroutines.launch
import com.ilhomsoliev.testappognam.features.login.viewmodel.LoginViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LoginScreen(vm: LoginViewModel, navController: NavController) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val phone by vm.phone.collectAsState()

    val country by country.collectAsState()

    val isNextActive = remember(phone, country) {
        val targetLength = (country.phoneMask.count { it == '#' }) + (country.clearPhoneDial.length)
        phone.length == targetLength
    }

    LoginContent(
        LoginState(
            phone, isNextActive,
            country,
        ), Modifier, object : LoginCallback {

            override fun onPhoneChange(text: String) {
                scope.launch { vm.changePhone(text) }
            }


            override fun onClear() {
                scope.launch { vm.clearPhone() }
            }

            override fun changeCountry() {
                scope.launch {
                    navController.navigate(Screens.SelectCountry)
                }
            }

            override fun onNext() {
                scope.launch {
                    val result = vm.sendCode()
                    result?.let {
                        Toast.makeText(
                            context, it,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    if (result == null)
                        navController.navigate(Screens.Code)
                }
            }
        }
    )
}