package ru.makiev.domain.model

data class OrderItemOption(
    val id: Int,
    val orderItemId: Int,
    val size: Size?,
    val additive: Additive?,
    val quantity: Int
)
