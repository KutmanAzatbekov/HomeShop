package com.geeks.homeshop.domain.usecases

import com.geeks.homeshop.domain.repository.CartRepository

class CheckoutUseCase (
    private val repository: CartRepository
) {
    suspend operator fun invoke(): Result<String> {
        return repository.checkout()
    }
}