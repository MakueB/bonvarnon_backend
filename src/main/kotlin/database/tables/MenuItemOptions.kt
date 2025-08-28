package ru.makiev.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object MenuItemOptions : IntIdTable("menu_item_options") {
    val menuItemId = integer("menu_item_id").references(MenuItems.id)
    val name = text("name")
    val additionalPrice = decimal("additional_price", 10, 2)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}