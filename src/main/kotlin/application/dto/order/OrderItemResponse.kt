package ru.makiev.application.dto.order

import kotlinx.serialization.Serializable

@Serializable
data class OrderItemResponse(
    val id: Int,
    val menuItemId: Int?,
    val quantity: Int,
    val options: List<OrderItemOptionResponse>
)
