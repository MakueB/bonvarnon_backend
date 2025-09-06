package ru.makiev.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateMenuItemRequest(
    val categoryId: Int,
    val name: String,
    val description: String? = null,
    val price: String,
    val imageUrl: String? = null
)
