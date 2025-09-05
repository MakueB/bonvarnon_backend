package ru.makiev.domain.api

import ru.makiev.domain.models.MenuCategory

interface MenuCategoryRepository {
    suspend fun getAll() : List<MenuCategory>
}