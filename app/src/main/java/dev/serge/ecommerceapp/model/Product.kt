package dev.serge.ecommerceapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class Product(
    @PrimaryKey val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val imageURL: String = "",
    val categoryId: String = "",
//    val description: String = ""
)
