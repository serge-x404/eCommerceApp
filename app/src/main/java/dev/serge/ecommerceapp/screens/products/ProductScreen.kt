package dev.serge.ecommerceapp.screens.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.serge.ecommerceapp.model.Product
import dev.serge.ecommerceapp.screens.navigation.Screens

@Composable
fun ProductScreen(
    categoryId: String,
    navController: NavController
) {
    // Fetch products from VM
    val products = listOf(
        Product("1","Smartphone",12000.00,"https://cdn-icons-png.flaticon.com/128/186/186239.png"),
        Product("2","Laptop",24000.00,"https://cdn-icons-png.flaticon.com/128/6062/6062646.png"),
        Product("3","Headphones",599.00,"https://cdn-icons-png.flaticon.com/128/3791/3791429.png"),
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "Products of category id: $categoryId",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )


        if (products.isEmpty()) {
            Text(
                "No products found!",
                modifier = Modifier.padding(16.dp)
            )
        }
        else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(products) {product ->
                    ProductItem(
                        product = product,
                        onClick = {navController.navigate(Screens.ProductDetails.createRoute(product.id))},
                        onAddToCart = {}
                    )
                }
            }
        }
    }
}