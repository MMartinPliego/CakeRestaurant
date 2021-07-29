package com.orumgames.cakesrestaurants.ui.main.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.orumgames.cakesrestaurants.R
import com.orumgames.cakesrestaurants.databinding.FragmentDetailRestaurantBinding
import com.orumgames.cakesrestaurants.domain.model.Restaurant

class DetailRestaurantFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailRestaurantBinding? = null
    private val binding get() = _binding!!
    lateinit var restaurant: Restaurant

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailRestaurantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            Glide.with(root.context)
                .load(restaurant.imageUrl)
                .error(R.drawable.ic_broken_image)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop()
                .into(imgCake)

            txtTitle.text = restaurant.title
            txtDesc.text = restaurant.desc
            txtAddress.text = restaurant.address
        }
    }

    companion object {
        val TAG = "DetailRestaurantFragment"
        @JvmStatic
        fun newInstance(restaurant: Restaurant) = DetailRestaurantFragment().apply {
            this.restaurant = restaurant
        }
    }
}