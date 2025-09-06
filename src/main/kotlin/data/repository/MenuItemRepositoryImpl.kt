package ru.makiev.data.repository

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import ru.makiev.data.database.tables.MenuItems
import ru.makiev.data.model.CreateMenuItemRequest
import ru.makiev.data.model.UpdateMenuItemRequest
import ru.makiev.dbQuery
import ru.makiev.domain.api.MenuItemRepository
import ru.makiev.domain.models.MenuItem

class MenuItemRepositoryImpl : MenuItemRepository {
    override suspend fun getAll(): List<MenuItem> {
        return dbQuery {
            MenuItems.selectAll().map { row ->
                MenuItem(
                    id = row[MenuItems.id].value,
                    name = row[MenuItems.name],
                    categoryId = row[MenuItems.categoryId],
                    description = row[MenuItems.description],
                    price = row[MenuItems.price],
                    imageUrl = row[MenuItems.imageUrl]
                )
            }
        }
    }

    override suspend fun getById(id: Int): MenuItem? {
        return dbQuery {
            MenuItems.selectAll()
                .where { MenuItems.id eq id }
                .map { row ->
                    MenuItem(
                        id = row[MenuItems.id].value,
                        name = row[MenuItems.name],
                        categoryId = row[MenuItems.categoryId],
                        description = row[MenuItems.description],
                        price = row[MenuItems.price],
                        imageUrl = row[MenuItems.imageUrl]
                    )
                }.singleOrNull()
        }
    }

    override suspend fun create(
        request: CreateMenuItemRequest
    ): MenuItem {
        return dbQuery {
            val id = MenuItems.insertAndGetId { row ->
                row[categoryId] = request.categoryId
                row[name] = request.name
                row[description] = request.description
                row[price] = request.price.toBigDecimal()
                row[imageUrl] = request.imageUrl
                row[createdAt] = CurrentDateTime
                row[updatedAt] = CurrentDateTime
            }.value

            MenuItem(
                id = id,
                categoryId = request.categoryId,
                name = request.name,
                description = request.description,
                price = request.price.toBigDecimal(),
                imageUrl = request.imageUrl
            )
        }
    }

    override suspend fun update(
        id: Int,
        request: UpdateMenuItemRequest
    ): MenuItem? {
        return dbQuery {
            val updated = MenuItems.update({ MenuItems.id eq id }) { row ->
                request.name?.let { row[name] = it }
                request.description?.let { row[description] = it }
                request.price?.let { row[price] = it.toBigDecimal() }
                request.imageUrl?.let { row[imageUrl] = it }
                request.categoryId?.let { row[categoryId] = it }
            }

            if (updated > 0) {
                MenuItems.selectAll()
                    .where { MenuItems.id eq id }
                    .map { row ->
                        MenuItem(
                            id = row[MenuItems.id].value,
                            name = row[MenuItems.name],
                            description = row[MenuItems.description],
                            price = row[MenuItems.price],
                            imageUrl = row[MenuItems.imageUrl],
                            categoryId = row[MenuItems.categoryId]
                        )
                    }.singleOrNull()
            } else null
        }
    }

    override suspend fun delete(id: Int): Boolean {
        return MenuItems.deleteWhere { MenuItems.id eq id } > 0
    }

    override suspend fun getByCategoryId(categoryId: Int): List<MenuItem> = dbQuery {
        MenuItems.selectAll()
            .where { MenuItems.categoryId eq categoryId }
            .map { row ->
                MenuItem(
                    id = row[MenuItems.id].value,
                    categoryId = row[MenuItems.categoryId],
                    name = row[MenuItems.name],
                    description = row[MenuItems.description],
                    price = row[MenuItems.price],
                    imageUrl = row[MenuItems.imageUrl]
                )
            }
    }
}