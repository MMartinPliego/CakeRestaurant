package com.orumgames.cakesrestaurants.ui.main.cake

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.orumgames.cakesrestaurants.R
import com.orumgames.cakesrestaurants.databinding.ItemCakeBinding
import com.orumgames.cakesrestaurants.domain.model.Cake

class CakeAdapter : RecyclerView.Adapter<CakeAdapter.ViewHolder>() {

    private lateinit var items: MutableList<Cake>
    lateinit var onItemClick: (cake: Cake) -> Unit

    inner class ViewHolder(private val binding: ItemCakeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cake: Cake) {
            binding.txtTitle.text = cake.title
            Glide.with(binding.root.context)
                .load(cake.imageUrl)
                .error(R.drawable.ic_broken_image)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop()
                .into(binding.imgCake)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCakeBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(::items.isInitialized)
            holder.bind(items[position])

        if(::onItemClick.isInitialized) {
            holder.itemView.setOnClickListener {
                onItemClick.invoke(items[position])
            }
        }
    }

    override fun getItemCount(): Int = if(::items.isInitialized) items.size else 0

    fun updateList(cakes: List<Cake>) {
        this.items = cakes.toMutableList()
        this.notifyDataSetChanged()
    }
}