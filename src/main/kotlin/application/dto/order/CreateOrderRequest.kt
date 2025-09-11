package ru.makiev.application.dto.order

import kotlinx.serialization.Serializable

@Serializable
data class CreateOrderRequest(
    val items: List<CreateOrderItemRequest>,
    val paymentMethod: String,
    val bonusUsed: Int? = 0
) {
    fun validate(): List<String> {
        val errors = mutableListOf<String>()
        if (items.isEmpty()) errors.add("Items must not be empty (empty order)")
        if (paymentMethod.isBlank()) errors.add("Payment method is required")
        return errors
    }
}

