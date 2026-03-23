package dev.serge.ecommerceapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.serge.ecommerceapp.model.Product
import dev.serge.ecommerceapp.repository.CartRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
): ViewModel() {

    val cartItems = repository.allCartItems

    fun addToCart(product: Product) =
        viewModelScope.launch {
            repository.addToCart(product)
        }

    fun deleteFromCart(product: Product) =
        viewModelScope.launch {
            repository.deleteFromCart(product)
        }

    fun clearCart() = viewModelScope.launch {
        repository.clearCart()
    }

    fun calculateTotal(items: List<Product>): Double {
        return items.sumOf { it.price }
    }
}