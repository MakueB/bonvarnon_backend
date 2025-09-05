package ru.makiev.data.repository

import org.jetbrains.exposed.sql.selectAll
import ru.makiev.data.database.tables.MenuItems
import ru.makiev.dbQuery
import ru.makiev.domain.api.MenuItemRepository
import ru.makiev.domain.models.MenuItem
import java.math.BigDecimal

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
        name: String,
        price: BigDecimal,
        imageUrl: String?
    ): MenuItem {
        TODO("Not yet implemented")
    }

    override suspend fun update(
        id: Int,
        name: String?,
        price: BigDecimal?,
        imageUrl: String?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
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