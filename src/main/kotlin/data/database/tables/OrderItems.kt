package ru.makiev.data.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object OrderItems : IntIdTable("order_items") {
    val orderId = integer("order_id").references(Orders.id, onDelete = ReferenceOption.CASCADE)
    val menuItemId = integer("menu_item_id").references(MenuItems.id).nullable()
    val quantity = integer("quantity")
}