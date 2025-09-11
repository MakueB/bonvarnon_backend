package ru.makiev.application.dto.order

import kotlinx.serialization.Serializable

@Serializable
data class OrderItemOptionRequest(
    val sizeId: Int? = null,
    val additivesId: Int? = null,
    val quantity: Int? = 1
)
