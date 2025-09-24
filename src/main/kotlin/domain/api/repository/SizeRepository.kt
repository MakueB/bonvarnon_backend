package ru.makiev.domain.api.repository

import ru.makiev.domain.model.Size

interface SizeRepository {
    suspend fun getById(id: Int): Size?
    suspend fun getAll(): List<Size>
    suspend fun create(size: Size): Int
    suspend fun update(size: Size): Boolean
    suspend fun delete(id: Int): Boolean
}