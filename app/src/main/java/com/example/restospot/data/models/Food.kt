package com.example.restospot.data.models

data class Food(
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val stock: Int,
    val description: String,
    val restaurant_id: Int,
    val category_id: Int
)


