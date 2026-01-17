package com.geeks.homeshop.ui.fragments.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil3.load
import coil3.request.crossfade
import com.geeks.homeshop.databinding.FragmentDetailBinding
import com.geeks.homeshop.databinding.FragmentListBinding
import com.geeks.homeshop.ui.models.UiState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailFragment: Fragment() {

    private var _binding: FragmentDetailBinding? = null

    private val binding get() = _binding!!

    private val args: ProductDetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = args.productId

        viewModel.load(productId)

        observeState()

    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when(state){
                    is UiState.Loading -> {

                    }

                    is UiState.Success -> {
                        val product = state.data
                        with(binding){
                           tvDetailTitle.text = product.title
                           tvDetailPrice.text = "${product.price} $"
                           tvDescription.text = product.description
                           ivDetail.load(product.image){
                               crossfade(true)
                           }
                        }
                    }

                    is UiState.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG)
                    }

                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}