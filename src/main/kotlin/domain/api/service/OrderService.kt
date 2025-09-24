package ru.makiev.domain.api.service

import ru.makiev.application.dto.order.CreateOrderRequest
import ru.makiev.application.dto.order.OrderResponse

interface OrderService {
    suspend fun getAll(): List<OrderResponse>
    suspend fun createOrder(userId: Int, request: CreateOrderRequest): OrderResponse
    suspend fun updateStatus(orderId: Int, statusId: Int): Boolean
    suspend fun getById(orderId: Int): OrderResponse?
    suspend fun getByUserId(userId: Int): List<OrderResponse>
}