package com.geeks.homeshop.ui.fragments.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.homeshop.databinding.FragmentListBinding
import com.geeks.homeshop.ui.adapters.ProductAdapter
import com.geeks.homeshop.ui.models.UiState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductListFragment: Fragment() {

    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding!!

    private val ListViewModel: ListViewModel by viewModel()

    private val adapter = ProductAdapter{product ->
        val action = ProductListFragmentDirections
            .actionProductListFragmentToProductDetailFragment(product.id)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        observeState()
    }

    private fun setupRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            ListViewModel.state.collect { state ->
                with(binding){
                    when(state){
                        is UiState.Loading -> {
                            progressBar.isVisible = true
                            recyclerView.isVisible = false
                        }
                        is UiState.Success -> {
                            progressBar.isVisible = false
                            recyclerView.isVisible = true
                            adapter.submitList(state.data)
                        }
                        is UiState.Error -> {
                            progressBar.isVisible = false
                            recyclerView.isVisible = false
                            Toast.makeText(requireContext(),state.message, Toast.LENGTH_LONG)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}