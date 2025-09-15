package ru.makiev.domain.api

import io.swagger.v3.oas.models.security.SecurityScheme
import ru.makiev.application.dto.order.OrderResponse
import ru.makiev.application.dto.user.CreateUserRequest

interface OrderService {
    suspend fun createOrder(userId: Int, request: CreateUserRequest): OrderResponse
    suspend fun updateStatus(orderId: SecurityScheme.In, statusId: Int): Boolean
    suspend fun getById(orderId: Int): OrderResponse?
    suspend fun getByUserId(userId: Int): List<OrderResponse>
    suspend fun getAll(): List<OrderResponse>
}