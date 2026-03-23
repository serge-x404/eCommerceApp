package dev.serge.ecommerceapp.screens.products

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import dev.serge.ecommerceapp.model.Product
import dev.serge.ecommerceapp.screens.navigation.Screens
import dev.serge.ecommerceapp.viewmodels.CartViewModel
import dev.serge.ecommerceapp.viewmodels.ProductViewModel

@Composable
fun ProductScreen(
    categoryId: String,
    navController: NavController,
    productViewModel: ProductViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel()
) {

    LaunchedEffect(categoryId) {
        productViewModel.fetchProducts(categoryId)
    }

    val productState = productViewModel.products.collectAsState()
    val products = productState.value

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
                        onAddToCart = {
                            cartViewModel.addToCart(product)
                            Log.v("TAGY","Product added to cart")
                        }
                    )
                }
            }
        }
    }
}