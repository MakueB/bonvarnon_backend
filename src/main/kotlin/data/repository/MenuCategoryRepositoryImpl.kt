package ru.makiev.data.repository

import org.jetbrains.exposed.sql.selectAll
import ru.makiev.data.database.tables.MenuCategories
import ru.makiev.dbQuery
import ru.makiev.domain.api.MenuCategoryRepository
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
}