package dev.serge.ecommerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.serge.ecommerceapp.screens.cart.CartScreen
import dev.serge.ecommerceapp.screens.home.HomeScreen
import dev.serge.ecommerceapp.screens.profile.ProfileScreen
import dev.serge.ecommerceapp.ui.theme.ECommerceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()


            NavHost(
                navController = navController,
                startDestination = "Home"
            ) {
                composable("Home") {
                    HomeScreen(
                        navController = navController,
                        onProfileClick = { navController.navigate("Profile") },
                        onCartClick = { navController.navigate("Cart") }
                    )
                }

                composable("Cart") {
                    CartScreen(navController = navController)
                }

                composable("Profile") {
                    ProfileScreen(
                        navController = navController,
                        onSignOut = {}
                    )
                }
            }
        }
    }
}