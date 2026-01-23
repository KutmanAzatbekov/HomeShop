package com.geeks.homeshop.ui.fragments.product.detail

import androidx.navigation.fragment.navArgs
import coil3.load
import coil3.request.crossfade
import com.geeks.homeshop.databinding.FragmentDetailBinding
import com.geeks.homeshop.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailFragment: BaseFragment<FragmentDetailBinding, DetailViewModel>(
    FragmentDetailBinding::inflate
) {

    private val args: ProductDetailFragmentArgs by navArgs()

    override val viewModel: DetailViewModel by viewModel()

    override fun onBind(binding: FragmentDetailBinding) {
        val productId = args.productId

        viewModel.load(productId)

        viewModel.state.collectUiState (
            onSuccess = { product ->
                with(binding) {
                    tvDetailTitle.text = product.title
                    tvDetailPrice.text = "${product.price} $"
                    tvDescription.text = product.description
                    ivDetail.load(product.image) {
                        crossfade(true)
                    }
                }
            }
        )
    }


}