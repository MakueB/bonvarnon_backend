package ru.makiev.domain.api.repository

import ru.makiev.application.dto.additive.CreateAdditiveRequest
import ru.makiev.application.dto.additive.UpdateAdditiveRequest
import ru.makiev.domain.model.Additive

interface AdditiveRepository {
    suspend fun getAll(): List<Additive>
    suspend fun getById(id: Int): Additive?
    suspend fun create(request: CreateAdditiveRequest): Additive
    suspend fun update(id: Int, request: UpdateAdditiveRequest): Additive?
    suspend fun delete(id: Int): Boolean
}