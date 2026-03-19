package dev.serge.ecommerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.serge.ecommerceapp.screens.cart.CartScreen
import dev.serge.ecommerceapp.screens.categories.CategoryScreen
import dev.serge.ecommerceapp.screens.home.HomeScreen
import dev.serge.ecommerceapp.screens.navigation.Screens
import dev.serge.ecommerceapp.screens.products.ProductDetailsScreen
import dev.serge.ecommerceapp.screens.products.ProductScreen
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
                startDestination = Screens.Home.route
            ) {
                composable(Screens.Home.route) {
                    HomeScreen(
                        navController = navController,
                        onProfileClick = { navController.navigate(Screens.Profile.route) },
                        onCartClick = { navController.navigate(Screens.Cart.route) }
                    )
                }

                composable(Screens.Cart.route) {
                    CartScreen(navController = navController)
                }

                composable(Screens.Profile.route) {
                    ProfileScreen(
                        navController = navController,
                        onSignOut = {}
                    )
                }

                composable(Screens.Categories.route) {
                    CategoryScreen(
                        navController = navController
                    )
                }

                composable(Screens.ProductDetails.route) {
                    val productId = it.arguments?.getString("productId")
                    if (productId != null) {
                        ProductDetailsScreen(productId)
                    }
                }

                composable(Screens.ProductList.route) {
                    val categoryId = it.arguments?.getString("categoryId")
                    if (categoryId != null) {
                        ProductScreen(
                            categoryId,
                            navController
                        )
                    }
                }
            }
        }
    }
}