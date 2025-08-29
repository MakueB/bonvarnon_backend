package ru.makiev.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object Orders : IntIdTable("orders") {
    val userId = integer("user_id").references(Users.id)
    val statusId = integer("status_id").references(OrderStatuses.id)
    val totalPrice = decimal("total_price", 10, 2)
    val paymentMethod = text("payment_method")
    val bonusUsed = integer("bonus_used").default(0)
    val bonusEarned = integer("bonus_earned").default(0)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}