package ru.makiev.domain.model

data class OrderItemOption(
    val id: Int,
    val orderItemId: Int,
    val sizeId: Int?,
    val additiveId: Int?,
    val quantity: Int
)
