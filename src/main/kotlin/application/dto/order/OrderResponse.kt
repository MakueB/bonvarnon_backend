package ru.makiev.application.dto.order

import kotlinx.serialization.Serializable

@Serializable
data class OrderResponse(
    val id: Int,
    val userId: Int,
    val status: String,
    val totalPrice: String,
    val paymentMethod: String,
    val bonusUsed: Int,
    val bonusEarned: Int,
    val createdAt: String,
    val updatedAt: String,
    val items: List<OrderItemResponse>
)
