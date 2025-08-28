package ru.makiev.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object MenuItems : IntIdTable("menu_items") {
    val categoryId = integer("category_id").references(MenuCategories.id)
    val name = text("text")
    val description = text("description").nullable()
    val price = decimal("price",10, 2)
    val imageUrl = text("image_url").nullable()
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}