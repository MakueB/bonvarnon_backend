package ru.makiev.application.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateMenuCategoryRequest (
    val name: String? = null,
    val description: String? = null
)