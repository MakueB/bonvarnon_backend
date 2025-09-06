package ru.makiev.domain.api

import ru.makiev.data.model.CreateMenuCategoryRequest
import ru.makiev.data.model.UpdateMenuCategoryRequest
import ru.makiev.domain.models.MenuCategory

interface MenuCategoryRepository {
    suspend fun getAll() : List<MenuCategory>
    suspend fun create(request: CreateMenuCategoryRequest) : MenuCategory
    suspend fun delete(id: Int): Boolean
    suspend fun update(id: Int, request: UpdateMenuCategoryRequest): MenuCategory?
}