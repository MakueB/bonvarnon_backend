package ru.makiev.domain.api

import ru.makiev.application.dto.order.CreateOrderRequest
import ru.makiev.domain.model.Order
import java.math.BigDecimal

interface OrderRepostory {
    suspend fun create(userId: Int, request: CreateOrderRequest, totalPrice: BigDecimal, bonusEarned: Int): Int
    suspend fun updateStatus(orderId: Int, statusId: Int): Boolean
    suspend fun findById(orderId: Int): Order?
    suspend fun findByUserId(userId: Int): List<Order>
}