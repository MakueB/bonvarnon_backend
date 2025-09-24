package ru.makiev.domain.api.service

import ru.makiev.application.dto.menu.CreateMenuCategoryRequest
import ru.makiev.application.dto.menu.CreateMenuItemRequest
import ru.makiev.application.dto.menu.UpdateMenuCategoryRequest
import ru.makiev.application.dto.menu.UpdateMenuItemRequest
import ru.makiev.domain.models.MenuCategory
import ru.makiev.domain.models.MenuItem

interface MenuService {
    suspend fun getFullMenu(): List<MenuCategory>

    suspend fun createCategory(request: CreateMenuCategoryRequest): MenuCategory

    suspend fun deleteCategory(id: Int): Boolean

    suspend fun updateCategory(id: Int, request: UpdateMenuCategoryRequest): MenuCategory?

    suspend fun createItem(request: CreateMenuItemRequest): MenuItem

    suspend fun deleteItem(id: Int): Boolean

    suspend fun updateItem(id: Int, request: UpdateMenuItemRequest): MenuItem?
}