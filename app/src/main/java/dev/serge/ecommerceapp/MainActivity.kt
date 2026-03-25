package dev.serge.ecommerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.serge.ecommerceapp.screens.cart.CartScreen
import dev.serge.ecommerceapp.screens.categories.CategoryScreen
import dev.serge.ecommerceapp.screens.home.HomeScreen
import dev.serge.ecommerceapp.screens.navigation.Screens
import dev.serge.ecommerceapp.screens.products.ProductDetailsScreen
import dev.serge.ecommerceapp.screens.products.ProductScreen
import dev.serge.ecommerceapp.screens.profile.LoginScreen
import dev.serge.ecommerceapp.screens.profile.ProfileScreen
import dev.serge.ecommerceapp.screens.profile.SignUpScreen
import dev.serge.ecommerceapp.ui.theme.ECommerceAppTheme
import dev.serge.ecommerceapp.viewmodels.AuthViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            val authViewModel: AuthViewModel = hiltViewModel()

            val isLoggedIn by remember { derivedStateOf { authViewModel.isLoggedIn } }


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
                        onSignOut = {
                            authViewModel.SignOut()
                            navController.navigate(Screens.Login.route)
                        }
                    )
                }

                composable(Screens.Categories.route) {
                    CategoryScreen(
                        navController = navController,
                        onCartClick = { navController.navigate(Screens.Cart.route) },
                        onProfileClick = {
                            if (isLoggedIn) {
                                navController.navigate(Screens.Profile.route)
                            }
                            else {
                                navController.navigate(Screens.Login.route)
                            }
                        }
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

                composable(Screens.SignUp.route) {
                    SignUpScreen(
                        onNavigateToLogin = {
                            navController.navigate(Screens.Login.route)
                        },
                        onSignUpSuccess = {
                            navController.navigate(Screens.Home.route)
                        }
                    )
                }

                composable(Screens.Login.route) {
                    LoginScreen(
                        navigateToSignUp = {navController.navigate(Screens.SignUp.route)},
                        onLoginSuccess = {navController.navigate(Screens.Home.route)}
                    )
                }
            }
        }
    }
}