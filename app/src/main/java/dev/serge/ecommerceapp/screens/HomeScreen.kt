package dev.serge.ecommerceapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

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
        }
    }
}