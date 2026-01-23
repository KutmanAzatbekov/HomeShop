package com.geeks.homeshop.ui.fragments.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.geeks.homeshop.R
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil3.load
import coil3.request.crossfade
import com.geeks.homeshop.databinding.FragmentListBinding
import com.geeks.homeshop.domain.models.Product
import com.geeks.homeshop.ui.adapters.ProductAdapter
import com.geeks.homeshop.ui.base.BaseFragment
import com.geeks.homeshop.ui.models.UiState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductListFragment: BaseFragment<FragmentListBinding, ListViewModel>(
    FragmentListBinding::inflate
) {
    override val viewModel: ListViewModel by viewModel()




    override fun onBind(binding: FragmentListBinding) {
        setupRecycler()

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_list, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_cart -> {
                        findNavController().navigate(R.id.action_productListFragment_to_cartFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        with(binding) {
            viewModel.state.collectUiState(
                onLoading = {
                    progressBar.isVisible = true
                    recyclerView.isVisible = false
                },
                onError = {
                    progressBar.isVisible = false
                    recyclerView.isVisible = false
                },
                onSuccess = { data ->
                    progressBar.isVisible = false
                    recyclerView.isVisible = true
                    adapter.submitList(data)
                }
            )
        }
    }

    private val adapter = ProductAdapter(
        onClick = {product ->
            val action = ProductListFragmentDirections
                .actionProductListFragmentToProductDetailFragment(product.id)
            findNavController().navigate(action)
        }, onBuyClick = {
                product ->
            viewModel.addToCart(product)
            Toast.makeText(context, "Добавлено!", Toast.LENGTH_SHORT).show()})

    private fun setupRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }
}