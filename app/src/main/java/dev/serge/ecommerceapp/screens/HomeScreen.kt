package dev.serge.ecommerceapp.screens

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import dev.serge.ecommerceapp.model.Categories

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { MyTopAppBar() },
        bottomBar = { BottomNavigationBar() }
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
                    /* Search Logic */
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )



            SectionTitle(
                "Categories",
                "See All"
            ) {}

            val categories: List<Categories> = listOf(
                Categories(1,"Electronics","https://cdn-icons-png.flaticon.com/128/12539/12539860.png"),
                Categories(2,"Clothing","https://cdn-icons-png.flaticon.com/128/2954/2954918.png"),
            )

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
                            selectedCategory.value = it
                        }
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Spacer(Modifier.height(16.dp))
            SectionTitle(
                "Featured",
                "See All"
            ) {}
        }
    }
}