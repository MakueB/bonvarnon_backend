package ru.makiev.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdateMenuCategoryRequest (
    val name: String? = null,
    val description: String? = null
)