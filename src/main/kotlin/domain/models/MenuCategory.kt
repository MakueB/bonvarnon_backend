package ru.makiev.domain.models

data class MenuCategory (
    val id: Int,
    val name: String,
    val description: String?,
    val items: List<MenuItem> = emptyList()
)