package dev.serge.ecommerceapp.screens

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar() {

    val currentRoute = ""

    val items = listOf(
        BottomNavItems("Home", Icons.Default.Home, "Home"),
        BottomNavItems("Categories", Icons.Default.Search, "Categories"),
        BottomNavItems("Wishlist", Icons.Default.Favorite, "Cart", 5),
        BottomNavItems("Cart", Icons.Default.ShoppingCart, "Cart",3),
        BottomNavItems("Profile", Icons.Default.Person, "Profile")
    )


    NavigationBar(
        modifier = Modifier.height(82.dp),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 8.dp
    ) {
        items.forEach {item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {},
                icon = {
                    if (item.badge > 0) {
                        BadgedBox(badge = {
                            Badge { Text(item.badge.toString()) }
                        }) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    else {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                label = { Text(item.title) }
            )
        }
    }



}


data class BottomNavItems(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val badge: Int = 0
)