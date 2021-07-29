package com.orumgames.cakesrestaurants.ui.main.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.orumgames.cakesrestaurants.R
import com.orumgames.cakesrestaurants.databinding.ItemRestaurantBinding
import com.orumgames.cakesrestaurants.domain.model.Restaurant

class RestaurantAdapter : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    private lateinit var items: MutableList<Restaurant>

    inner class ViewHolder(private val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.txtTitle.text = restaurant.title
            binding.txtAddress.text = restaurant.address
            Glide.with(binding.root.context)
                .load(restaurant.imageUrl)
                .error(R.drawable.ic_broken_image)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop()
                .into(binding.imgRestaurant)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(::items.isInitialized)
            holder.bind(items[position])
    }

    override fun getItemCount(): Int = if(::items.isInitialized) items.size else 0

    fun updateList(restaurants: List<Restaurant>) {
        this.items = restaurants.toMutableList()
        this.notifyDataSetChanged()
    }
}