package com.example.restospot

import android.R
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restospot.data.adapters.CategoryFoodAdapter
import com.example.restospot.data.adapters.TopRestaurantAdapter
import com.example.restospot.data.network.RetrofitInstance
import com.example.restospot.databinding.ActivityMainBinding
import com.example.restospot.databinding.HomeTabBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException


class MainActivity : AppCompatActivity() {


    private lateinit var retrofit: RetrofitInstance
    private lateinit var topRestaurantAdapter: TopRestaurantAdapter
    private lateinit var categoryFoodAdapter: CategoryFoodAdapter
    private lateinit var binding: HomeTabBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeTabBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupRecyclerView2()

        retrofit = RetrofitInstance


        lifecycleScope.launchWhenCreated {
            val response = try {
                retrofit.api.getCategoryFoods()
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection $e")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
               categoryFoodAdapter.foods = response.body()!!
            } else {
                Log.e(TAG, "${response}")
            }
        }

        lifecycleScope.launchWhenCreated {
            val response = try {
                retrofit.api.getRestaurants()
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection $e")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                topRestaurantAdapter.restaurants = response.body()!!
            } else {
                Log.e(TAG, "${response.body()!!}")
            }
        }




    }

    private fun setupRecyclerView() = binding.topRestaurantList.apply {
        topRestaurantAdapter = TopRestaurantAdapter()
        adapter = topRestaurantAdapter
        layoutManager =  LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun setupRecyclerView2() = binding.foodList.apply {
        categoryFoodAdapter = CategoryFoodAdapter(this@MainActivity)
        adapter = categoryFoodAdapter

        layoutManager = GridLayoutManager(this@MainActivity,2)
    }


}