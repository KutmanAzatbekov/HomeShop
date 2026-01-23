package com.geeks.homeshop.domain.repository

import com.geeks.homeshop.domain.models.CartItem
import com.geeks.homeshop.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    val cartItems: Flow<List<CartItem>>

    suspend fun addToCart(product: Product)
    suspend fun checkout(): Result<String>
    suspend fun clearCart()



}