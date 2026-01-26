package com.geeks.homeshop.ui.fragments.cart

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.homeshop.databinding.FragmentCartBinding
import com.geeks.homeshop.ui.adapters.CartAdapter
import com.geeks.homeshop.ui.base.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : BaseFragment<FragmentCartBinding, CartViewModel>(
    FragmentCartBinding::inflate
) {
    override val viewModel: CartViewModel by viewModel()

    private val adapter = CartAdapter()

    override fun onBind(binding: FragmentCartBinding) {
        setupRecycler()

        viewModel.items.collectFlow { state ->
            adapter.submitList(state)
            binding.btnCheckout.isEnabled = state.isNotEmpty()
        }

        viewModel.total.collectFlow { state ->
            binding.tvTotalValue.text = "$ %.2f".format(state)
        }

        binding.btnCheckout.setOnClickListener {
            viewModel.checkout()
        }

    }

    private fun setupRecycler() {
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCart.adapter = adapter
    }

}