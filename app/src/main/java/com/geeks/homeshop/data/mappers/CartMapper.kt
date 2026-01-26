package com.geeks.homeshop.data.mappers

import com.geeks.homeshop.data.model.CartProductDto
import com.geeks.homeshop.domain.models.CartItem

fun CartItem.toDto(): CartProductDto {
    return CartProductDto(
        productId = this.product.id,
        quantity = this.quantity
    )
}