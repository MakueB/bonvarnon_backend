package ru.makiev.application.dto.order

import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderItemRequest(
    val menuItemId: Int,
    val quantity: Int,
    val options: List<OrderItemOptionRequest>? = emptyList()
)
