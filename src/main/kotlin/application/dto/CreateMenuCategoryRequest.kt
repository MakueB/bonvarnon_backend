package ru.makiev.application.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateMenuCategoryRequest(
    val name: String,
    val description: String? = null
) {
    fun validate(): List<String> {
        val errors = mutableListOf<String>()
        if (name.isBlank()) errors.add("Name must not be blank")
        if (name.length > 50) errors.add("Name must not exceed 50 characters")
        return errors
    }
}
