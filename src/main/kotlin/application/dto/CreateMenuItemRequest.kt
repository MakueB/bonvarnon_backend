package ru.makiev.application.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateMenuItemRequest(
    val categoryId: Int,
    val name: String,
    val description: String? = null,
    val price: String,
    val imageUrl: String? = null
) {
    fun validate(): List<String> {
        val errors = mutableListOf<String>()
        if (name.isBlank()) errors.add("Name must not be blank")
        if (price.toBigDecimal() == null) errors.add("Price must be a valid number")
        return errors
    }
}
