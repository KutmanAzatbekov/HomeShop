package com.geeks.homeshop.domain.models

data class CartItem(
    val product: Product,
    val quantity: Int = 1
)