package com.geeks.homeshop.data.repository

import com.geeks.homeshop.data.datasourse.StoreApi
import com.geeks.homeshop.data.mappers.toDomain
import com.geeks.homeshop.domain.models.Product
import com.geeks.homeshop.domain.repository.ProductRepository

class ProductRepositoryImpl(private val storeApi: StoreApi): ProductRepository {
    override suspend fun getProducts(): List<Product> =
        storeApi.getAllProducts().map { productDto ->
        productDto.toDomain()
    }

    override suspend fun getProductById(productId: Int): Product =
        storeApi.getProductById(productId).toDomain()


}