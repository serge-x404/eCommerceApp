package dev.serge.ecommerceapp.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import dev.serge.ecommerceapp.model.Category
import dev.serge.ecommerceapp.model.Product
import dev.serge.ecommerceapp.screens.navigation.Screens
import dev.serge.ecommerceapp.viewmodels.CategoryViewModel
import dev.serge.ecommerceapp.viewmodels.ProductViewModel
import dev.serge.ecommerceapp.viewmodels.SearchViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    onProfileClick: () -> Unit,
    onCartClick: () -> Unit,
    productViewModel: ProductViewModel = hiltViewModel(),
    categoryViewModel: CategoryViewModel = hiltViewModel(),
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { MyTopAppBar(onProfileClick, onCartClick) },
        bottomBar = { BottomNavigationBar(navController) }
    ) { it ->
        Column(modifier =
            Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            val searchQuery = remember { mutableStateOf("") }
            val focusManager = LocalFocusManager.current

            SearchBar(
                query = searchQuery.value,
                onQueryChange = {searchQuery.value = it},
                onSearch = {
                     searchViewModel.searchProducts(searchQuery.value)
                    focusManager.clearFocus()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            if (searchQuery.value.isNotBlank()) {
                SearchResultsSection(navController)
            }

            SectionTitle(
                "Categories",
                "See All",
                { navController.navigate(Screens.Categories.route) }
            )

            val categoriesState = categoryViewModel.categories.collectAsState()
            val categories = categoriesState.value

            val selectedCategory = remember { mutableStateOf(0) }

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories.size) {
                    CategoryChip(
                        icon = categories[it].iconURL,
                        text = categories[it].name,
                        isSelected = selectedCategory.value == it,
                        onClick = {
                            selectedCategory.value = categories[it].id
                            navController.navigate(Screens.ProductList
                                .createRoute(selectedCategory.value.toString())
                            )
                        }
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            SectionTitle(
                "Featured",
                "See All",
            ) {navController.navigate(Screens.Categories.route)}
            Spacer(Modifier.height(16.dp))

            productViewModel.getAllProductsInFirestore()

            val products = productViewModel.allProducts.collectAsState()
            val allProductsFound = products.value

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(allProductsFound){product ->
                    FeaturedProductCard(product) {
                        navController.navigate(
                            Screens.ProductDetails.createRoute(product.id)
                        )
                    }
                }
            }
        }
    }
}