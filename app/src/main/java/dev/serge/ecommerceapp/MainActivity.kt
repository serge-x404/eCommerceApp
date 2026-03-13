package dev.serge.ecommerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.serge.ecommerceapp.screens.home.HomeScreen
import dev.serge.ecommerceapp.ui.theme.ECommerceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ECommerceAppTheme {

                HomeScreen()

            }
        }
    }
}