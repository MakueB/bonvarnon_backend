package ru.makiev.application.dto.order

import kotlinx.serialization.Serializable

@Serializable
data class OrderItemOptionResponse(
    val sizeId: Int?,
    val sizeName: String?,
    val additiveId: Int?,
    val additiveName: String?,
    val quantity: Int
)


