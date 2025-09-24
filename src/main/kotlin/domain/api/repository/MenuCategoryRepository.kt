package ru.makiev.domain.api.repository

import ru.makiev.application.dto.menu.CreateMenuCategoryRequest
import ru.makiev.application.dto.menu.UpdateMenuCategoryRequest
import ru.makiev.domain.models.MenuCategory

interface MenuCategoryRepository {
    suspend fun getAll() : List<MenuCategory>
    suspend fun create(request: CreateMenuCategoryRequest) : MenuCategory
    suspend fun delete(id: Int): Boolean
    suspend fun update(id: Int, request: UpdateMenuCategoryRequest): MenuCategory?
}