package ru.makiev.database.tables

import org.jetbrains.exposed.sql.Table

object Roles : Table("roles") {
    val id = integer("id").autoIncrement()
    val name = text("name").uniqueIndex()
    override val primaryKey = PrimaryKey(id)
}