package dev.serge.ecommerceapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.serge.ecommerceapp.model.Product
import dev.serge.ecommerceapp.repository.FireStoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: FireStoreRepository
): ViewModel() {

    private val _searchResults = MutableStateFlow<List<Product>>(emptyList())
    val searchResults: MutableStateFlow<List<Product>> get() = _searchResults

    private val _isSearching = MutableStateFlow(false)
    val isSearching: MutableStateFlow<Boolean> get() = _isSearching

    fun searchProducts(query: String) {
        if (query.isBlank()) {
            _searchResults.value = emptyList()
            _isSearching.value = false
            return
        }
        else {
            _isSearching.value = true
            viewModelScope.launch {
                _searchResults.value = repository.searchProduct(query)
                _isSearching.value = false
            }
        }
    }
}