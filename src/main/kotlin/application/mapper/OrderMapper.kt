package ru.makiev.application.mapper

import ru.makiev.application.dto.order.OrderItemOptionResponse
import ru.makiev.application.dto.order.OrderItemResponse
import ru.makiev.application.dto.order.OrderResponse
import ru.makiev.domain.model.Order
import ru.makiev.domain.model.OrderItem
import ru.makiev.domain.model.OrderItemOption
import ru.makiev.domain.model.User

fun Order.toOrderResponse(user: User, status: String): OrderResponse {
    return OrderResponse(
        id = this.id,
        user = user.toResponse(),
        status = status,
        totalPrice = this.totalPrice.toPlainString(),
        paymentMethod = this.paymentMethod,
        bonusUsed = this.bonusUsed,
        bonusEarned = this.bonusEarned,
        createdAt = this.createdAt.toString(),
        updatedAt = this.updatedAt.toString(),
        items = this.items.map { it.toOrderItemResponse() }
    )
}

fun OrderItem.toOrderItemResponse(): OrderItemResponse {
    return OrderItemResponse(
        id = this.id,
        menuItemId = this.menuItemId,
        quantity = this.quantity,
        options = this.options.map { it.toOrderItemOptionResponse() }
    )
}

fun OrderItemOption.toOrderItemOptionResponse(): OrderItemOptionResponse {
    return OrderItemOptionResponse(
        sizeId = this.size?.id,
        sizeName = this.size?.name,
        additiveId = this.additive?.id,
        additiveName = this.additive?.name,
        quantity = this.quantity
    )
}