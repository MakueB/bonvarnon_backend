package ru.makiev.application.dto.additive

import kotlinx.serialization.Serializable

@Serializable
data class UpdateAdditiveRequest(
    val id: Int,
    val name: String? = null,
    val extraPrice: String? = null
)
