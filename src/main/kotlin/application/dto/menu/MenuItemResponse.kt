package ru.makiev.application.dto.menu

import kotlinx.serialization.Serializable

@Serializable
data class MenuItemResponse(
    val id: Int,
    val name: String,
    val description: String?,
    val price: String,
    val imageUrl: String?,
)
