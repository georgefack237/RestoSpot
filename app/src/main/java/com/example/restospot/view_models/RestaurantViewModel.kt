package com.example.restospot.view_models
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restospot.data.models.FoodCategory
import java.util.Locale.Category


class RestaurantViewModel() : ViewModel() {

    // Visible only by the ViewModel

    private var _genresLiveData = mutableListOf<FoodCategory>()



     var genresLiveData = _genresLiveData

    init {
        getGenres()

    }







    private fun getGenres(){

          val categories = mutableListOf<FoodCategory>(FoodCategory(1,"chicken"),FoodCategory(2,"fries"))


       _genresLiveData = categories
    }

}