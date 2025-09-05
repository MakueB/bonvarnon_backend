package ru.makiev.domain.api

import ru.makiev.domain.models.MenuCategory

interface MenuService {
    suspend fun getFullMenu(): List<MenuCategory>
}