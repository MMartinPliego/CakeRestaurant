package com.orumgames.cakesrestaurants.ui.main.cake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.orumgames.cakesrestaurants.databinding.FragmentCakesBinding
import com.orumgames.cakesrestaurants.di.ViewModelProviderFactory
import com.orumgames.cakesrestaurants.domain.model.Cake
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CakesFragment : DaggerFragment() {

    private lateinit var cakesViewModel: CakesViewModel
    private var _binding: FragmentCakesBinding? = null
    private val adapter: CakeAdapter by lazy {
        CakeAdapter().apply {
            onItemClick = ::onCakeClick
        }
    }

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cakesViewModel =
            ViewModelProvider(requireActivity(), providerFactory).get(CakesViewModel::class.java)

        _binding = FragmentCakesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvList.adapter = adapter
        binding.rvList.addItemDecoration(
            DividerItemDecoration(
                requireActivity(),
                LinearLayoutManager.VERTICAL
            )
        )

        binding.swipeContainer.setOnRefreshListener {
            cakesViewModel.getAllCakes()
        }

        binding.swipeContainer.setColorSchemeColors(
            resources.getColor(android.R.color.holo_blue_bright, null),
            resources.getColor(android.R.color.holo_green_light, null),
            resources.getColor(android.R.color.holo_orange_light, null),
            resources.getColor(android.R.color.holo_red_light, null)
        )

        cakesViewModel.cakes.observe(viewLifecycleOwner, {
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
        cakesViewModel.getAllCakes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onCakeClick(cake: Cake) {
        childFragmentManager.let {
            DetailCakeFragment.newInstance(cake).apply {
                show(it, DetailCakeFragment.TAG)
            }
        }
    }
}