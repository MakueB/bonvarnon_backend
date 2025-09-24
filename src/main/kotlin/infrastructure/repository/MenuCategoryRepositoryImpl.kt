package ru.makiev.data.repository

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import ru.makiev.data.database.tables.MenuCategories
import ru.makiev.application.dto.menu.CreateMenuCategoryRequest
import ru.makiev.application.dto.menu.UpdateMenuCategoryRequest
import ru.makiev.plugins.dbQuery
import ru.makiev.domain.api.repository.MenuCategoryRepository
import ru.makiev.domain.models.MenuCategory

class MenuCategoryRepositoryImpl : MenuCategoryRepository {
    override suspend fun getAll(): List<MenuCategory> {
        return dbQuery {
            MenuCategories.selectAll().map { row ->
                MenuCategory(
                    id = row[MenuCategories.id].value,
                    name = row[MenuCategories.name],
                    description = row[MenuCategories.description]
                )
            }
        }
    }

    override suspend fun create(request: CreateMenuCategoryRequest): MenuCategory {
        return dbQuery {
            val id = MenuCategories.insertAndGetId { row ->
                row[name] = request.name
                row[description] = request.description
                row[createdAt] = CurrentDateTime
                row[updatedAt] = CurrentDateTime
            }.value

            MenuCategory(id, request.name, request.description)
        }
    }

    override suspend fun delete(id: Int): Boolean {
        return dbQuery {
            MenuCategories.deleteWhere { MenuCategories.id eq id } > 0
        }
    }

    override suspend fun update(id: Int, request: UpdateMenuCategoryRequest): MenuCategory? {
        return dbQuery {
            val updated = MenuCategories.update({ MenuCategories.id eq id }) { row ->
                request.name?.let { row[name] = it }
                request.description?.let { row[description] = it }
                row[updatedAt] = CurrentDateTime
            }

            if (updated > 0) {
                MenuCategories.selectAll()
                    .where { MenuCategories.id eq id }
                    .map { row ->
                        MenuCategory(
                            id = row[MenuCategories.id].value,
                            name = row[MenuCategories.name],
                            description = row[MenuCategories.description]
                        )
                    }.singleOrNull()
            } else null
        }
    }
}