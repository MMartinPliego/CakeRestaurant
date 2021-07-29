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
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CakesFragment : DaggerFragment() {

    private lateinit var cakesViewModel: CakesViewModel
    private var _binding: FragmentCakesBinding? = null
    private val adapter: CakeAdapter by lazy { CakeAdapter() }

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cakesViewModel = ViewModelProvider(requireActivity(), providerFactory).get(CakesViewModel::class.java)

        _binding = FragmentCakesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvList.adapter = adapter
        binding.rvList.addItemDecoration(DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL))

        cakesViewModel.cakes.observe(viewLifecycleOwner, {
            adapter.updateList(it)
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
}