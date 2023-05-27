package com.ilhomsoliev.testappognam.features.login.presentation.code

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ilhomsoliev.testappognam.app.navigation.Screens
import com.ilhomsoliev.testappognam.features.login.viewmodel.CodeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
@Composable
fun CodeScreen(vm: CodeViewModel, navController: NavController) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val focuses by vm.focuses.collectAsState()
    val code by vm.code.collectAsState()
    val timer by vm.timer.collectAsState()

    LaunchedEffect(Unit) {
        vm.firstFocus()
        while (true) {
            delay(1000L)
            vm.onTimerChange()
        }
    }

    CodeContent(
        CodeState(code, focuses, timer),
        Modifier, object : CodeCallback {

            override fun onCodeChange(index: Int, text: String) {
                scope.launch {
                    vm.onCodeChange(index, text)

                    if (vm.code.value.length == vm.codeLength.value) try {
                        when (vm.checkAuthCode(vm.code.value)) {
                            false -> {
                                navController.navigate(Screens.AuthProfile)
                            }
                            null -> {
                                badCode()
                            }
                            else -> {
                                navController.navigate(Screens.ChatList)
                            }
                        }
                    } catch (e: Exception) {
                        e.stackTraceToString()
                        badCode()
                    }
                }
            }

            private suspend fun badCode() {
                scope.launch {
                    Toast.makeText(context, "Плохой код", Toast.LENGTH_LONG).show()
                    vm.onCodeClear()
                }
            }


            override fun onCodeSend() {
                scope.launch {
                    //
                }
            }

            override fun onBack() {
                navController.popBackStack()
            }
        }
    )
}