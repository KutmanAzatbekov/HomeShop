package com.geeks.homeshop.domain.usecases

import com.geeks.homeshop.domain.models.Product
import com.geeks.homeshop.domain.repository.ProductRepository

class GetProductByIdUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: Int): Product =
        repository.getProductById(productId = productId)

}