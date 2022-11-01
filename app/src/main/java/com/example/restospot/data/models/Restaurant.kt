package com.example.restospot.data.models

data class Restaurant(
    val id: Int,
    val name: String,
    val country: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val  phone: Long,
    val rating: Int,
    val reviews: Int
)