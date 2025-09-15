package ru.makiev.domain.service

import io.swagger.v3.oas.models.security.SecurityScheme
import ru.makiev.application.dto.order.CreateOrderRequest
import ru.makiev.application.dto.order.OrderResponse
import ru.makiev.application.dto.user.CreateUserRequest
import ru.makiev.domain.api.*
import java.math.BigDecimal

class OrderServiceImpl(
    private val orderRepository: OrderRepostory,
    private val userRepository: UserRepository,
    private val menuItemRepository: MenuItemRepository,
    private val additiveRepository: AdditiveRepository
) : OrderService {
    override suspend fun createOrder(
        userId: Int,
        request: CreateUserRequest
    ): OrderResponse {

    }

    suspend fun CreateOrderRequest.calculateTotalPrice(
        menuItemRepository: MenuItemRepository,
        additiveRepository: AdditiveRepository
    ): BigDecimal {
        var total = BigDecimal.ZERO

        for (item in items) {
            val menuItem = menuItemRepository.getById(item.menuItemId)
                ?: throw IllegalArgumentException("Menu item ${item.menuItemId} not found")

            val itemPrice = menuItem.price

            item.options?.forEach { option ->
                option.sizeId?.let {
                    val size = 
                }
            }
        }
    }

    override suspend fun updateStatus(
        orderId: SecurityScheme.In,
        statusId: Int
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getById(orderId: Int): OrderResponse? {
        TODO("Not yet implemented")
    }

    override suspend fun getByUserId(userId: Int): List<OrderResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<OrderResponse> {
        TODO("Not yet implemented")
    }

}