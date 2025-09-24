package ru.makiev.domain.api.service

import ru.makiev.application.dto.size.CreateSizeRequest
import ru.makiev.application.dto.size.SizeResponse
import ru.makiev.application.dto.size.UpdateSizeRequest

interface SizeService {
    suspend fun getById(id: Int): SizeResponse?
    suspend fun getAll(): List<SizeResponse>
    suspend fun create(request: CreateSizeRequest): Int
    suspend fun update(id: Int, request: UpdateSizeRequest): Boolean
    suspend fun delete(id: Int): Boolean
}