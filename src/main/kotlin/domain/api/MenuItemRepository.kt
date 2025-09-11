package ru.makiev.domain.api

import ru.makiev.application.dto.menu.CreateMenuItemRequest
import ru.makiev.application.dto.menu.UpdateMenuItemRequest
import ru.makiev.domain.models.MenuItem

interface MenuItemRepository {
    suspend fun getAll(): List<MenuItem>
    suspend fun getById(id: Int): MenuItem?
    suspend fun create(request: CreateMenuItemRequest): MenuItem
    suspend fun update(id: Int, request: UpdateMenuItemRequest): MenuItem?
    suspend fun delete(id: Int): Boolean
    suspend fun getByCategoryId(categoryId: Int): List<MenuItem>
}