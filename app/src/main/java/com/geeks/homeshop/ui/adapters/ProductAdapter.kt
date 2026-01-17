package com.geeks.homeshop.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.geeks.homeshop.databinding.ItemProductBinding
import com.geeks.homeshop.domain.models.Product

class ProductAdapter(
    private val onClick: (Product) -> Unit
): ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffUtilCallback()) {

    class ProductDiffUtilCallback: DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        ))



    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding){
            tvTitle.text = item.title
            tvPrice.text = "${item.price} $"
            tvCategory.text = item.category

            ivProduct.load(item.image){
                crossfade(true)
            }

            root.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }



    inner class ProductViewHolder( val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root)
}