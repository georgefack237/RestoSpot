package com.example.restospot.data.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.restospot.data.models.Restaurant
import com.example.restospot.databinding.HotelCardBinding
import com.squareup.picasso.Picasso


class TopRestaurantAdapter : RecyclerView.Adapter<TopRestaurantAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: HotelCardBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(oldItem:Restaurant, newItem: Restaurant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var restaurants: List<Restaurant>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun getItemCount() = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            HotelCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val restaurant = restaurants[position]
            hotelName.text = restaurant.name
            hotelLocation.text = "${restaurant.country}, ${restaurant.city}"
            hotelRating.rating = restaurant.rating.toFloat()
            hotelReviews.text = "(${restaurant.reviews})"
            Picasso.get()
                .load(restaurant.image)
                .fit()
                .centerCrop()

                .into(hotelImage);

        }

    }
}

