package com.business.swey.core.models

data class CartItemDto (
    var id: Int,
    var count: Int,
    val product: ProductDto
)
