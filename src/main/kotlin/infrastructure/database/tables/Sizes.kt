package ru.makiev.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.math.BigDecimal

object Sizes : IntIdTable("sizes") {
    val name = varchar("name", 50)
    val extraPrice = decimal("extra_price", 10, 2).default(BigDecimal.ZERO)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}