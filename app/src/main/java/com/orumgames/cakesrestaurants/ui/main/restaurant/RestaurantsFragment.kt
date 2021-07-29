package com.orumgames.cakesrestaurants.ui.main.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.orumgames.cakesrestaurants.databinding.FragmentRestaurantsBinding
import com.orumgames.cakesrestaurants.di.ViewModelProviderFactory
import com.orumgames.cakesrestaurants.domain.model.Cake
import com.orumgames.cakesrestaurants.domain.model.Restaurant
import com.orumgames.cakesrestaurants.ui.main.cake.DetailCakeFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RestaurantsFragment : DaggerFragment() {

    private lateinit var restaurantsViewModel: RestaurantsViewModel
    private var _binding: FragmentRestaurantsBinding? = null
    private val adapter: RestaurantAdapter by lazy { RestaurantAdapter().apply {
        onItemClick = ::onRestaurantClick
    } }

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        restaurantsViewModel = ViewModelProvider(requireActivity(), providerFactory).get(RestaurantsViewModel::class.java)

        _binding = FragmentRestaurantsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvList.adapter = adapter
        binding.rvList.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                LinearLayoutManager.VERTICAL
            )
        )

        binding.swipeContainer.setOnRefreshListener {
            restaurantsViewModel.getAllRestaurants()
        }

        binding.swipeContainer.setColorSchemeColors(
            resources.getColor(android.R.color.holo_blue_bright, null),
            resources.getColor(android.R.color.holo_green_light, null),
            resources.getColor(android.R.color.holo_orange_light, null),
            resources.getColor(android.R.color.holo_red_light, null)
        )

        restaurantsViewModel.restaurants.observe(viewLifecycleOwner, {
            if(it.isEmpty())
                binding.txtMsg.visibility = View.VISIBLE
            else {
                adapter.updateList(it)
                binding.txtMsg.visibility = View.GONE
            }
            binding.swipeContainer.isRefreshing = false
        })
        return root
    }

    override fun onResume() {
        super.onResume()
        restaurantsViewModel.getAllRestaurants()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onRestaurantClick(restaurant: Restaurant) {
        childFragmentManager.let {
            DetailRestaurantFragment.newInstance(restaurant).apply {
                show(it, DetailCakeFragment.TAG)
            }
        }
    }
}