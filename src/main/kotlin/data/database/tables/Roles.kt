package ru.makiev.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object Roles : IntIdTable("roles") {
    val name = text("name").uniqueIndex()
}