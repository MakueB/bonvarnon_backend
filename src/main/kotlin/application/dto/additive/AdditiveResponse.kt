package ru.makiev.application.dto.additive

import kotlinx.serialization.Serializable

@Serializable
data class AdditiveResponse(
    val id: Int,
    val name: String,
    val extraPrice: String
)
