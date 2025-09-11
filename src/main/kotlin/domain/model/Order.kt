package ru.makiev.domain.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class Order(
    val id: Int,
    val statusId: Int,
    val totalPrice: BigDecimal,
    val paymentMethod: String,
    val bonusUsed: Int,
    val bonusEarned: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val items: List<OrderItem> = emptyList()
)
