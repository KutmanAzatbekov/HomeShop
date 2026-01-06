package com.geeks.homeshop.domain.repository

import com.geeks.homeshop.domain.models.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProductById(productId: Int): Product
}