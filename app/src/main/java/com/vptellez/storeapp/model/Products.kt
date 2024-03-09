package com.vptellez.storeapp.model
data class Products(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: ProductsRating
)
data class ProductsRating(
    val rate: Double,
    val count: Int
)