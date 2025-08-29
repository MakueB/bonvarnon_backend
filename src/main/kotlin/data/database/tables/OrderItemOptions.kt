package ru.makiev.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object OrderItemOptions : IntIdTable("order_item_options") {
    val orderItem = reference("order_item_id", OrderItems, onDelete = ReferenceOption.CASCADE)
    val size = reference("size_id", Sizes).nullable()
    val additive = reference("additive_id", Additives).nullable()
    val quantity = integer("quantity").default(1)
}