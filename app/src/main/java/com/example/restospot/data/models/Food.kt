package com.example.restospot.data.models

data class Food(
    val id: Int,
    val name: String,
    val price: String,
    val currentStock: Int,
    val restaurantId: Int,
    val categoryId: Int
)
