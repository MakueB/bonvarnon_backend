package ru.makiev.data.model

data class UpdateMenuItemRequest(
    val name: String? = null,
    val description: String? = null,
    val price: String? = null,
    val imageUrl: String? = null,
    val categoryId: Int? = null
)
