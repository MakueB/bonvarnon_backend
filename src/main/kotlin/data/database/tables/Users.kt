package ru.makiev.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime


object Users : IntIdTable("users") {
    val email = text("email").nullable()
    val phone = text("phone").nullable()
    val passwordHash = text("password_hash").nullable()
    val name = text("name")
    val avatarUrl = text("avatar_url").nullable()
    val roleId = integer("role_id").references(Roles.id)
    val bonusBalance = integer("bonus_balance").default(0)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}