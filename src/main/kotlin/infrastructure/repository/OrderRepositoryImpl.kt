package ru.makiev.infrastructure.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import ru.makiev.application.dto.order.CreateOrderRequest
import ru.makiev.data.database.tables.Additives
import ru.makiev.data.database.tables.OrderItemOptions
import ru.makiev.data.database.tables.OrderItems
import ru.makiev.data.database.tables.OrderStatuses
import ru.makiev.data.database.tables.Orders
import ru.makiev.data.database.tables.Sizes
import ru.makiev.domain.api.repository.OrderRepostory
import ru.makiev.domain.model.Additive
import ru.makiev.domain.model.Order
import ru.makiev.domain.model.OrderItem
import ru.makiev.domain.model.OrderItemOption
import ru.makiev.domain.model.Size
import ru.makiev.plugins.dbQuery
import java.math.BigDecimal

class OrderRepositoryImpl: OrderRepostory {
    override suspend fun getAll(): List<Order> {
        return dbQuery {
            Orders.selectAll().map { row ->row.toOrder() }
        }
    }

    override suspend fun create(
        userId: Int,
        request: CreateOrderRequest,
        totalPrice: BigDecimal,
        bonusEarned: Int
    ): Int {
        return dbQuery {
            val orderId = Orders.insertAndGetId { row ->
                row[Orders.userId] = userId
                row[Orders.statusId] = 1
                row[Orders.totalPrice] = totalPrice
                row[Orders.paymentMethod] = request.paymentMethod
                row[Orders.bonusUsed] = request.bonusUsed ?: 0
                row[Orders.bonusEarned] = bonusEarned
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

            orderId
        }
    }

    override suspend fun updateStatus(orderId: Int, statusId: Int): Boolean {
        val updated = dbQuery {
            Orders.update({ Orders.id eq orderId }) { row ->
                row[Orders.statusId] = statusId
                row[Orders.updatedAt] = CurrentDateTime
            }
        }
        return updated > 0
    }

    override suspend fun getById(orderId: Int): Order? {
        return dbQuery {
            Orders.selectAll()
                .where { Orders.id eq orderId }
                .map { row -> row.toOrder() }
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
            (OrderItemOptions leftJoin Sizes leftJoin Additives)
                .selectAll()
                .where { OrderItemOptions.orderItemId eq itemId }
                .map { row ->
                    val size = row[OrderItemOptions.sizeId].let {
                        Size(
                            id = row[Sizes.id].value,
                            name = row[Sizes.name],
                            extraPrice = row[Sizes.extraPrice],
                            createdAt = row[Sizes.createdAt],
                            updatedAt = row[Sizes.updatedAt]
                        )
                    }

                    val additive = row[OrderItemOptions.additiveId].let {
                        Additive(
                            id = row[Additives.id].value,
                            name = row[Additives.name],
                            extraPrice = row[Additives.extraPrice]
                        )
                    }

                    OrderItemOption(
                        id = row[OrderItemOptions.id].value,
                        orderItemId = itemId,
                        size = size,
                        additive = additive,
                        quantity = row[OrderItemOptions.quantity]
                    )
                }
        }
    }

    override suspend fun getByUserId(userId: Int): List<Order> {
        return dbQuery {
            Orders.selectAll()
                .where { Orders.userId eq userId }
                .map { row ->
                    row.toOrder()
                }
        }
    }

    override suspend fun getStatusName(statusId: Int): String? {
        return dbQuery {
            OrderStatuses.select(OrderStatuses.name)
                .where { OrderStatuses.id eq statusId }
                .map { it[OrderStatuses.name] }
                .singleOrNull()
        }
    }

    private suspend fun ResultRow.toOrder(): Order {
        return  Order(
            id = this[Orders.id].value,
            userId = this[Orders.userId],
            statusId = this[Orders.statusId],
            totalPrice = this[Orders.totalPrice],
            paymentMethod = this[Orders.paymentMethod],
            bonusUsed = this[Orders.bonusUsed],
            bonusEarned = this[Orders.bonusEarned],
            createdAt = this[Orders.createdAt],
            updatedAt = this[Orders.updatedAt],
            items = getItems(this[Orders.id].value)
        )
    }
}