package ru.makiev.application.dto.menu

import kotlinx.serialization.Serializable

@Serializable
data class UpdateMenuCategoryRequest (
    val name: String? = null,
    val description: String? = null
)