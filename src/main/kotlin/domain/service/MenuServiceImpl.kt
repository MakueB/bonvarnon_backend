package ru.makiev.domain.service

import ru.makiev.domain.api.MenuCategoryRepository
import ru.makiev.domain.api.MenuItemRepository
import ru.makiev.domain.api.MenuService
import ru.makiev.domain.models.MenuCategory

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
}