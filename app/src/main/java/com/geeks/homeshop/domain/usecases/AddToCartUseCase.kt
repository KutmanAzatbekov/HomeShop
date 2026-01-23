package com.geeks.homeshop.domain.usecases

import com.geeks.homeshop.domain.models.Product
import com.geeks.homeshop.domain.repository.CartRepository

class AddToCartUseCase (
    private val repository: CartRepository
) {
    suspend operator fun invoke(product: Product) {
        return repository.addToCart(product)
    }
}