package ru.makiev.domain.service

import ru.makiev.application.dto.additive.CreateAdditiveRequest
import ru.makiev.application.dto.additive.UpdateAdditiveRequest
import ru.makiev.domain.api.AdditiveRepository
import ru.makiev.domain.api.AdditiveService
import ru.makiev.domain.model.Additive

class AdditiveServiceImpl(
    private val additiveRepository: AdditiveRepository
) : AdditiveService {
    override suspend fun getAll(): List<Additive> = additiveRepository.getAll()

    override suspend fun getById(id: Int): Additive? = additiveRepository.getById(id)

    override suspend fun create(request: CreateAdditiveRequest): Additive = additiveRepository.create(request)

    override suspend fun update(id: Int, request: UpdateAdditiveRequest): Additive? = additiveRepository.update(id, request)

    override suspend fun delete(id: Int): Boolean = additiveRepository.delete(id)

}