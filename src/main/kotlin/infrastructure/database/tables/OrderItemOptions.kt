package ru.makiev.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object OrderItemOptions : IntIdTable("order_item_options") {
    val orderItemId = reference("order_item_id", OrderItems, onDelete = ReferenceOption.CASCADE)
    val sizeId = reference("size_id", Sizes).nullable()
    val additiveId = reference("additive_id", Additives).nullable()
    val quantity = integer("quantity").default(1)
}