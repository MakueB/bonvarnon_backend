package ru.makiev.domain.api

import ru.makiev.application.dto.CreateAdditiveRequest
import ru.makiev.application.dto.UpdateAdditiveRequest
import ru.makiev.domain.model.Additive

interface AdditiveService {
    suspend fun getAll(): List<Additive>
    suspend fun getById(id: Int): Additive?
    suspend fun create(request: CreateAdditiveRequest): Additive
    suspend fun update(id: Int, request: UpdateAdditiveRequest): Additive?
    suspend fun delete(id: Int): Boolean
}