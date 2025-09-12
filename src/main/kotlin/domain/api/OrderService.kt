package ru.makiev.domain.api

import ru.makiev.application.dto.order.OrderResponse

interface OrderService {
    suspend fun createOrder(): OrderResponse
    suspend fun updateOrderStatus()
    suspend fun getOrderById(orderId: Int): OrderResponse?
    suspend fun getOrdersByUser(userId: Int): List<OrderResponse>
}