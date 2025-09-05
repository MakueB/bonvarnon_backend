package ru.makiev.domain.api

import ru.makiev.domain.models.MenuItem
import java.math.BigDecimal

interface MenuItemRepository {
    suspend fun getAll(): List<MenuItem>
    suspend fun getById(id: Int): MenuItem?
    suspend fun create(name: String, price: BigDecimal, imageUrl: String?): MenuItem
    suspend fun update(id: Int, name: String?, price: BigDecimal?, imageUrl: String?): Boolean
    suspend fun delete(id: Int): Boolean
    suspend fun getByCategoryId(categoryId: Int): List<MenuItem>
}