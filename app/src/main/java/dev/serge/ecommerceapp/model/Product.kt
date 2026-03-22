package dev.serge.ecommerceapp.model

data class Product(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val imageURL: String = "",
    val categoryId: String = "",
//    val description: String = ""
)
