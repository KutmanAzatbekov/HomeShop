package com.geeks.homeshop.domain.usecases

import com.geeks.homeshop.domain.models.CartItem
import com.geeks.homeshop.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class GetCartItemsUseCase(
    private val repository: CartRepository
) {
    operator fun invoke(): Flow<List<CartItem>> {
        return repository.cartItems
    }
}