package com.geeks.homeshop.domain.usecases

import com.geeks.homeshop.domain.repository.CartRepository

class ClearCartUseCase (
    private val repository: CartRepository
) {
    suspend operator fun invoke() {
        return repository.clearCart()
    }
}