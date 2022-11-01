package com.example.restospot

import android.R
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restospot.data.adapters.TopRestaurantAdapter
import com.example.restospot.data.network.RetrofitInstance
import com.example.restospot.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import retrofit2.HttpException
import java.io.IOException


class MainActivity : AppCompatActivity() {


    private lateinit var topRestaurantAdapter: TopRestaurantAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        var lastCheckedId = View.NO_ID
        binding.categoryChips.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == View.NO_ID) {
                // User tried to uncheck, make sure to keep the chip checked
                group.check(lastCheckedId)
                return@setOnCheckedChangeListener
            }
            lastCheckedId = checkedId




        }


        binding.categoryChips.setOnCheckedChangeListener() { chipGroup, i ->
            val chip = chipGroup.findViewById<Chip>(i)
            if (chip != null) Toast.makeText(
                applicationContext,
                "Chip is " + chip.chipText,
                Toast.LENGTH_SHORT
            ).show()
        }





        lifecycleScope.launchWhenCreated {

            val response = try {
                RetrofitInstance.api.getRestaurants()
            } catch(e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection $e")
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
               topRestaurantAdapter.restaurants = response.body()!!
            } else {
                Log.e(TAG, "${ response.body()!!}")
            }

        }







    }

    private fun setupRecyclerView() = binding.topRestaurantList.apply {
        topRestaurantAdapter = TopRestaurantAdapter()
        adapter = topRestaurantAdapter
        layoutManager =  LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,true)
    }
}