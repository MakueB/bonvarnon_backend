package ru.makiev.data.mapper

import ru.makiev.data.model.MenuCategoryResponse
import ru.makiev.data.model.MenuItemResponse
import ru.makiev.domain.models.MenuCategory


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