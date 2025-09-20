package ru.makiev.domain.service

import ru.makiev.application.dto.order.CreateOrderRequest
import ru.makiev.application.dto.order.OrderResponse
import ru.makiev.application.mapper.toOrderResponse
import ru.makiev.domain.api.*
import java.math.BigDecimal

class OrderServiceImpl(
    private val orderRepository: OrderRepostory,
    private val userRepository: UserRepository,
    private val menuItemRepository: MenuItemRepository,
    private val additiveRepository: AdditiveRepository,
    private val sizeRepository: SizeRepository
) : OrderService {

    companion object{
        const val BONUS_PERCENT = 7
    }

    override suspend fun createOrder(
        userId: Int,
        request: CreateOrderRequest
    ): OrderResponse {
        val totalPrice = request.calculateTotalPrice(menuItemRepository, additiveRepository, sizeRepository)
        val bonusEarned = totalPrice.toInt() / BONUS_PERCENT

        val orderId = orderRepository.create(userId, request, totalPrice, bonusEarned)
        val order = orderRepository.getById(orderId)
            ?: throw IllegalStateException("Order not found after creation")
        val user = userRepository.getById(userId)
            ?: throw IllegalStateException("User not found for order")
        val status = orderRepository.getStatusName(order.statusId)
            ?: throw IllegalStateException("Status ${order.statusId} not found")

        return order.toOrderResponse(user, status)
    }

    suspend fun CreateOrderRequest.calculateTotalPrice(
        menuItemRepository: MenuItemRepository,
        additiveRepository: AdditiveRepository,
        sizeRepository: SizeRepository
    ): BigDecimal {
        var total = BigDecimal.ZERO

        for (item in items) {
            val menuItem = menuItemRepository.getById(item.menuItemId)
                ?: throw IllegalArgumentException("Menu item ${item.menuItemId} not found")

            var itemPrice = menuItem.price

            item.options?.forEach { option ->
                option.sizeId?.let {
                    val size = sizeRepository.getById(it)
                        ?: throw IllegalArgumentException("Size $it not found")
                    itemPrice += size.extraPrice
                }

                option.additivesId?.let {
                    val additive = additiveRepository.getById(it)
                        ?: throw IllegalArgumentException("Additive $it not found")
                    itemPrice += additive.extraPrice
                }
            }

            total += itemPrice * BigDecimal(item.quantity)
        }
        return total
    }

    override suspend fun updateStatus(
        orderId: Int,
        statusId: Int
    ): Boolean {
        return orderRepository.updateStatus(orderId, statusId)
    }

    override suspend fun getById(orderId: Int): OrderResponse? {
        val order = orderRepository.getById(orderId) ?: return null
        val user = userRepository.getById(order.userId) ?: throw IllegalStateException("User not found for order")
        val status = orderRepository.getStatusName(order.statusId)
            ?: throw IllegalStateException("Status ${order.statusId} not found")
        return order.toOrderResponse(user, status)
    }

    override suspend fun getByUserId(userId: Int): List<OrderResponse> {
        val user = userRepository.getById(userId) ?: throw IllegalStateException("User not found for order")
        return orderRepository.getByUserId(userId).map { order ->
            val status = orderRepository.getStatusName(order.statusId)
                ?: throw IllegalStateException("Status ${order.statusId} not found")
            order.toOrderResponse(user, status)
        }
    }

    override suspend fun getAll(): List<OrderResponse> {
        return orderRepository.getAll().map { order ->
            val user = userRepository.getById(order.userId) ?: throw IllegalStateException("User not found for order")
            val status = orderRepository.getStatusName(order.statusId)
                ?: throw IllegalStateException("Status ${order.statusId} not found")
            order.toOrderResponse(user, status)
        }
    }

}