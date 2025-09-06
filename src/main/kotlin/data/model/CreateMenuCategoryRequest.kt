package ru.makiev.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateMenuCategoryRequest(
    val name: String,
    val description: String? = null
)
