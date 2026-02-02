package com.example.jks_compose_kt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.jks_compose_kt.navigation.MainNavigation
import com.example.jks_compose_kt.ui.theme.Jks_compose_ktTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jks_compose_ktTheme {
                MainNavigation(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}