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
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RestaurantsFragment : DaggerFragment() {

    private lateinit var restaurantsViewModel: RestaurantsViewModel
    private var _binding: FragmentRestaurantsBinding? = null
    private val adapter: RestaurantAdapter by lazy { RestaurantAdapter() }

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

        restaurantsViewModel.restaurants.observe(viewLifecycleOwner, {
            adapter.updateList(it)
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
}