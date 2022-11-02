package com.example.restospot.data.network

import com.example.restospot.data.models.Food
import com.example.restospot.data.models.Restaurant
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface RestaurantApi {

    @GET("api/restaurants")
    suspend fun getRestaurants(): Response<List<Restaurant>>


    @GET("api/categoryFoods")
   suspend fun getCategoryFoods(): Response<List<Food>>

    @POST("/restaurant_foods")
    fun getRestaurantFoods(@Body restaurant: String): Response<List<Food>>


    @POST("/register")
    fun apiRequest(@Query("key") key: String): Response<String>


}
