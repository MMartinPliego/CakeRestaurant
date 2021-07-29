package com.orumgames.cakesrestaurants.ui.main.cake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.orumgames.cakesrestaurants.R
import com.orumgames.cakesrestaurants.databinding.FragmentDetailCakeBinding
import com.orumgames.cakesrestaurants.domain.model.Cake

class DetailCakeFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailCakeBinding? = null
    private val binding get() = _binding!!
    lateinit var cake: Cake

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailCakeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            Glide.with(root.context)
                .load(cake.imageUrl)
                .error(R.drawable.ic_broken_image)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop()
                .into(imgCake)

            txtTitle.text = cake.title
            txtDesc.text = cake.desc
        }
    }

    companion object {
        val TAG = "DetailCakeFragment"
        @JvmStatic
        fun newInstance(cake: Cake) = DetailCakeFragment().apply {
            this.cake = cake
        }
    }
}