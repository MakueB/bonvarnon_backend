package ru.makiev.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import java.math.BigDecimal

object Sizes : IntIdTable("sizes") {
    val name = varchar("name", 50)
    val extraPrice = decimal("extra_price", 10, 2).default(BigDecimal.ZERO)
}