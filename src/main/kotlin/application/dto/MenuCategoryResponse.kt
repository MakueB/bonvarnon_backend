package ru.makiev.application.dto

import kotlinx.serialization.Serializable

@Serializable
data class MenuCategoryResponse(
    val id: Int,
    val name: String,
    val description: String?,
    val items: List<MenuItemResponse> = emptyList()
)
