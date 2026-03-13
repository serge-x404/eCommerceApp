package dev.serge.ecommerceapp.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = {
            Text(
                "eCommerceApp",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        ),
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Shopping Cart",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profiles",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    )
}