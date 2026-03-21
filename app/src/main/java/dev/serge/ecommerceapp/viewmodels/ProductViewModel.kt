package dev.serge.ecommerceapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.serge.ecommerceapp.model.Product
import dev.serge.ecommerceapp.repository.FireStoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: FireStoreRepository
): ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())

    val products: StateFlow<List<Product>> get() = _products

    fun fetchProducts(categoryId: String) {
        viewModelScope.launch {
            try {
                val products = repository.getProductsByCategory(categoryId)
                _products.value = products
            }
            catch (e: Exception) {
                Log.e("TAGY","Error fetching products ${e.message}")
            }
        }
    }

    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())
    val allProducts: StateFlow<List<Product>> get() = _allProducts

    fun getAllProductsInFirestore() {
        viewModelScope.launch {
            try {
                val allProducts = repository.getAllProductsInFireStore()
                _allProducts.value = allProducts
            }
            catch (e: Exception) {
                Log.e("TAGY","Error fetching all products ${e.message}")
            }
        }
    }
}