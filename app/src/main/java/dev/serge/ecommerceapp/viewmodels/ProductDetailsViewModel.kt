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
class ProductDetailsViewModel @Inject constructor(
    private val repository: FireStoreRepository
): ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)

    val product: StateFlow<Product?> get() = _product

    fun fetchProductDetails(productId: String) {
        viewModelScope.launch {
            try {
                val product = repository.getProductById(productId)
                _product.value = product
            }
            catch (e: Exception) {
                // Handle error
            }
        }
    }
}