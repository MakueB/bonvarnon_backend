package ru.makiev.domain.model

data class OrderItem(
    val id: Int,
    val orderId: Int,
    val menuItemId: Int?,
    val quantity: Int,
    val options: List<OrderItemOption> = emptyList()
)
