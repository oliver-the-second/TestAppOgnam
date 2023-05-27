package com.ilhomsoliev.testappognam.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ilhomsoliev.testappognam.app.navigation.Navigation
import com.ilhomsoliev.testappognam.ui.theme.TestAppOgnamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAppOgnamTheme {
                Navigation()
            }
        }
    }
}