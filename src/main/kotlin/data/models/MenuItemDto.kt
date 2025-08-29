package ru.makiev.data.models

import org.jetbrains.exposed.sql.ResultRow
import ru.makiev.data.database.tables.MenuItems

data class MenuItemDto(
    val id: Int,
    val name: String,
    val description: String?,
    val price: Double,
    val categoryId: Int
)

fun  ResultRow.toMenuItemDto() = MenuItemDto(
    id = this[MenuItems.id].value,
    name = this[MenuItems.name],
    description = this[MenuItems.description],
    price = this[MenuItems.price].toDouble(),
    categoryId = this[MenuItems.categoryId]
)
