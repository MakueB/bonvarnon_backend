package ru.makiev.infrastructure.repository

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import ru.makiev.application.dto.order.CreateOrderRequest
import ru.makiev.data.database.tables.OrderItemOptions
import ru.makiev.data.database.tables.OrderItems
import ru.makiev.data.database.tables.Orders
import ru.makiev.domain.api.OrderRepostory
import ru.makiev.domain.model.Order
import ru.makiev.domain.model.OrderItem
import ru.makiev.domain.model.OrderItemOption
import ru.makiev.plugins.dbQuery
import java.math.BigDecimal

class OrderRepositoryImpl: OrderRepostory {
    override suspend fun create(
        userId: Int,
        request: CreateOrderRequest,
        totalPrice: BigDecimal,
        bonusEarned: Int
    ): Int {
        return dbQuery {
            val odrerId = Orders.insertAndGetId { row ->
                row[Orders.userId] = userId
                row[Orders.statusId] = 1
                row[Orders.totalPrice] = totalPrice
                row[Orders.paymentMethod] = request.paymentMethod
                row[Orders.bonusUsed] = request.bonusUsed ?: 0
                row[Orders.bonusEarned] = bonusUsed
                row[Orders.createdAt] = CurrentDateTime
                row[Orders.updatedAt] = CurrentDateTime
            }.value

            request.items.forEach { item ->
                val itemId = OrderItems.insertAndGetId { row ->
                    row[OrderItems.orderId] = orderId
                    row[OrderItems.menuItemId] = item.menuItemId
                    row[OrderItems.quantity] = item.quantity
                }.value

                item.options?.forEach { option ->
                    OrderItemOptions.insert { row ->
                        row[OrderItemOptions.orderItemId] = itemId
                        row[OrderItemOptions.sizeId] = option.sizeId
                        row[OrderItemOptions.additiveId] = option.additivesId
                        row[OrderItemOptions.quantity] = option.quantity ?: 1
                    }
                }
            }

            odrerId
        }
    }

    override suspend fun updateStatus(orderId: Int, statusId: Int) {
        dbQuery {
            Orders.update({ Orders.id eq orderId }) { row ->
                row[Orders.statusId] = statusId
                row[Orders.updatedAt] = CurrentDateTime
            }
        }
    }

    override suspend fun findById(orderId: Int): Order? {
        return dbQuery {
            Orders.selectAll()
                .where { Orders.id eq orderId }
                .map { row ->
                    Order(
                        id = orderId,
                        statusId = row[Orders.statusId],
                        totalPrice = row[Orders.totalPrice],
                        paymentMethod = row[Orders.paymentMethod],
                        bonusUsed = row[Orders.bonusUsed],
                        bonusEarned = row[Orders.bonusEarned],
                        createdAt = row[Orders.createdAt],
                        updatedAt = row[Orders.updatedAt],
                        items = getItems(orderId)
                    )
                }
        }.singleOrNull()
    }

    private suspend fun getItems(orderId: Int): List<OrderItem> {
        return dbQuery {
            OrderItems.selectAll()
                .where { OrderItems.orderId eq orderId }
                .map { row ->
                    OrderItem(
                        id = row[OrderItems.id].value,
                        orderId = orderId,
                        menuItemId = row[OrderItems.menuItemId],
                        quantity = row[OrderItems.quantity],
                        options = getOptions(row[OrderItems.id].value)
                    )
                }
        }
    }

    private suspend fun getOptions(itemId: Int): List<OrderItemOption> {
        return dbQuery {
            OrderItemOptions.selectAll()
                .where { OrderItemOptions.orderItemId eq itemId }
                .map { row ->
                    OrderItemOption(
                        id = row[OrderItemOptions.id].value,
                        orderItemId = itemId,
                        sizeId = row[OrderItemOptions.sizeId]?.value,
                        additiveId = row[OrderItemOptions.additiveId]?.value ,
                        quantity = row[OrderItemOptions.quantity]
                    )
                }
        }
    }

    override suspend fun findByUserId(userId: Int): List<Order> {
        return dbQuery {
            Orders.selectAll()
                .where { Orders.userId eq userId }
                .map { row ->
                    Order(
                        id = row[Orders.id].value,
                        statusId = row[Orders.statusId],
                        totalPrice = row[Orders.totalPrice],
                        paymentMethod = row[Orders.paymentMethod],
                        bonusEarned = row[Orders.bonusEarned],
                        bonusUsed = row[Orders.bonusUsed],
                        createdAt = row[Orders.createdAt],
                        updatedAt = row[Orders.updatedAt],
                        items = getItems(row[Orders.id].value)
                    )
                }
        }
    }
}