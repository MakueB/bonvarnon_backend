package ru.makiev.application.dto.size

import kotlinx.serialization.Serializable

@Serializable
data class SizeResponse(
    val id: Int,
    val name: String,
    val extraPrice: String,
    val createdAt: String,
    val updatedAt: String
)
