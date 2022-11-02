package com.example.restospot.data.adapters
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.restospot.FoodDetails
import com.example.restospot.data.models.Food
import com.example.restospot.data.models.Restaurant
import com.example.restospot.databinding.FoodItemBinding

import com.squareup.picasso.Picasso


class CategoryFoodAdapter(private var context: Context) : RecyclerView.Adapter< CategoryFoodAdapter.CategoryFoodViewHolder>() {

    inner class CategoryFoodViewHolder(val binding: FoodItemBinding) : RecyclerView.ViewHolder(binding.root)


    private val diffCallback = object : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem:Food, newItem: Food): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem:Food, newItem: Food): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var foods: List<Food>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun getItemCount() = foods.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryFoodViewHolder {
        return CategoryFoodViewHolder(
          FoodItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))


    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryFoodViewHolder, position: Int) {
        holder.binding.apply {
            val food = foods[position]

            foodName.text = food.name
            foodSold.text = "${food.stock} Sold"
            foodPrice.text = "${food.price} XAF"
            foodStock.text = "${food.stock} Left"
            Picasso.get()
                .load(food.image)
                .fit()
                .centerCrop()
                .into(foodImage);

            holder.itemView.setOnClickListener {
                val intent = Intent(context, FoodDetails::class.java)
                context.startActivity(intent)
            }

        }




    }
}

