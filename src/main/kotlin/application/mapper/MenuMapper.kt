package ru.makiev.application.mapper

import ru.makiev.application.dto.menu.MenuCategoryResponse
import ru.makiev.application.dto.menu.MenuItemResponse
import ru.makiev.domain.models.MenuCategory
import ru.makiev.domain.models.MenuItem


fun MenuCategory.toMenuCategoryResponse(): MenuCategoryResponse =
    MenuCategoryResponse(
        id = this.id,
        name = this.name,
        description = this.description,
        items = this.items.map {
            MenuItemResponse(
                id = it.id,
                name = it.name,
                description = it.description,
                price = it.price.toPlainString(),
                imageUrl = it.imageUrl
            )
        }
    )

fun MenuItem.toMenuItemResponse(): MenuItemResponse =
    MenuItemResponse(
        id = this.id,
        name = this.name,
        description = this.description,
        price = this.price.toPlainString(),
        imageUrl = this.imageUrl
    )