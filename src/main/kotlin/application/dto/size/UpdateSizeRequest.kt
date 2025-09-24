package ru.makiev.application.dto.size

import kotlinx.serialization.Serializable

@Serializable
data class UpdateSizeRequest(
    val name: String? = null,
    val extraPrice: String? = null
)
