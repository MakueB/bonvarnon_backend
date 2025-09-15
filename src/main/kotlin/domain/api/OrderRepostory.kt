package ru.makiev.domain.api

import ru.makiev.application.dto.order.CreateOrderRequest
import ru.makiev.domain.model.Order
import java.math.BigDecimal

interface OrderRepostory {
    suspend fun getAll(): List<Order>
    suspend fun create(userId: Int, request: CreateOrderRequest, totalPrice: BigDecimal, bonusEarned: Int): Int
    suspend fun updateStatus(orderId: Int, statusId: Int): Boolean
    suspend fun getById(orderId: Int): Order?
    suspend fun getByUserId(userId: Int): List<Order>
}