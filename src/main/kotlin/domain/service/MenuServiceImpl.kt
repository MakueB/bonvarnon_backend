package ru.makiev.domain.service

import ru.makiev.application.dto.CreateMenuCategoryRequest
import ru.makiev.application.dto.CreateMenuItemRequest
import ru.makiev.application.dto.UpdateMenuCategoryRequest
import ru.makiev.application.dto.UpdateMenuItemRequest
import ru.makiev.domain.api.MenuCategoryRepository
import ru.makiev.domain.api.MenuItemRepository
import ru.makiev.domain.api.MenuService
import ru.makiev.domain.models.MenuCategory
import ru.makiev.domain.models.MenuItem

class MenuServiceImpl(
    private val categoryRepository: MenuCategoryRepository,
    private val itemRepository: MenuItemRepository
) : MenuService {
    override suspend fun getFullMenu(): List<MenuCategory> {
        val categories = categoryRepository.getAll()
        return categories.map { category ->
            val items = itemRepository.getByCategoryId(category.id)
            category.copy(items = items)
        }
    }

    override suspend fun createCategory(request: CreateMenuCategoryRequest): MenuCategory {
        return categoryRepository.create(request)
    }

    override suspend fun deleteCategory(id: Int): Boolean = categoryRepository.delete(id)

    override suspend fun updateCategory(
        id: Int,
        request: UpdateMenuCategoryRequest
    ): MenuCategory? = categoryRepository.update(id, request)

    override suspend fun createItem(request: CreateMenuItemRequest): MenuItem = itemRepository.create(request)

    override suspend fun deleteItem(id: Int): Boolean = itemRepository.delete(id)

    override suspend fun updateItem(
        id: Int,
        request: UpdateMenuItemRequest
    ): MenuItem? = itemRepository.update(id, request)
}