package ru.makiev.application.dto.order

import kotlinx.serialization.Serializable

@Serializable
data class OrderItemOptionResponse(
    val sizeId: Int?,
    val size: String?,
    val additiveId: Int?,
    val quantity: Int
)


