package ru.makiev.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object MenuCategories : IntIdTable("menu_categories") {
    val name = text("name")
    val description = text("description").nullable()
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}