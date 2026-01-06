package com.geeks.homeshop.domain.usecases

import com.geeks.homeshop.domain.models.Product
import com.geeks.homeshop.domain.repository.ProductRepository

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> = repository.getProducts()
}