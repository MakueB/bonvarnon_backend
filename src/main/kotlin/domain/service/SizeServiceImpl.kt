package ru.makiev.domain.service

import ru.makiev.application.dto.size.CreateSizeRequest
import ru.makiev.application.dto.size.SizeResponse
import ru.makiev.application.dto.size.UpdateSizeRequest
import ru.makiev.domain.api.repository.SizeRepository
import ru.makiev.domain.api.service.SizeService
import ru.makiev.domain.model.Size
import java.math.BigDecimal
import java.time.Instant

class SizeServiceImpl(
    private val sizeRepository: SizeRepository
) : SizeService {
    override suspend fun getById(id: Int): SizeResponse? {
        return sizeRepository.getById(id)?.toResponse()
    }

    override suspend fun getAll(): List<SizeResponse> {
        return sizeRepository.getAll().map { it.toResponse() }
    }

    override suspend fun create(request: CreateSizeRequest): Int {
        val id = sizeRepository.create(
            Size(
                id = 0,
                name = request.name,
                extraPrice = request.extraPrice.toBigDecimal(),
                createdAt = Instant.now(),
                updatedAt = Instant.now()
            )
        )
        return id
    }

    override suspend fun update(id: Int, request: UpdateSizeRequest): Boolean {
        val existing = sizeRepository.getById(id) ?: return false

        return sizeRepository.update(
            existing.copy(
                id = id,
                name = request.name ?: existing.name,
                extraPrice = BigDecimal(request.extraPrice),
                updatedAt = Instant.now()
            )
        )
    }

    override suspend fun delete(id: Int): Boolean {
        return sizeRepository.delete(id)
    }

    private fun Size.toResponse(): SizeResponse = SizeResponse(
            id = this.id,
            name = this.name,
            extraPrice = this.extraPrice.toPlainString(),
            createdAt = this.createdAt.toString(),
            updatedAt = this.updatedAt.toString()
        )
}