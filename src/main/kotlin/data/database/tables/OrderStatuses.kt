package ru.makiev.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object OrderStatuses : IntIdTable("order_statuses") {
    val name = text("name").uniqueIndex()
}