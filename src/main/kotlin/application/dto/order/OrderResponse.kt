package ru.makiev.application.dto.order

import kotlinx.serialization.Serializable
import ru.makiev.application.dto.user.UserResponse

@Serializable
data class OrderResponse(
    val id: Int,
    val user: UserResponse,
    val status: String,
    val totalPrice: String,
    val paymentMethod: String,
    val bonusUsed: Int,
    val bonusEarned: Int,
    val createdAt: String,
    val updatedAt: String,
    val items: List<OrderItemResponse>
)
