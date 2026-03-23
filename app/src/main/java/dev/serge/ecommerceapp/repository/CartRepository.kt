package dev.serge.ecommerceapp.repository

import android.util.Log
import dev.serge.ecommerceapp.model.Product
import dev.serge.ecommerceapp.room.CartDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartDao: CartDao
) {

    val allCartItems: Flow<List<Product>> =
        cartDao.getAllCartItems()

    suspend fun addToCart(product: Product) {
        val existingItems = cartDao.getCartItemById(product.id)

        if (existingItems != null) {
            cartDao.updateCartItem(product)
            Log.v("TAGY","Product already added")
        }
        else {
            cartDao.insertCartItem(product)
            Log.v("TAGY","Product added!!")
        }
    }

    suspend fun deleteFromCart(product: Product) {
        cartDao.deleteCartItem(product)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }
}