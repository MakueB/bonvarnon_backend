package ru.makiev.domain.service

import ru.makiev.application.dto.user.CreateUserRequest
import ru.makiev.application.dto.user.UpdateUserRequest
import ru.makiev.application.dto.user.UserResponse
import ru.makiev.application.mapper.toResponse
import ru.makiev.domain.api.service.PasswordService
import ru.makiev.domain.api.repository.UserRepository
import ru.makiev.domain.api.service.UserService
import ru.makiev.domain.model.User
import ru.makiev.domain.model.UserRole
import java.time.LocalDateTime

class UserSeviceImpl(
    private val userRepository: UserRepository,
    private val passwordService: PasswordService
): UserService {
    override suspend fun getAll(): List<UserResponse> = userRepository.getAll().map { it.toResponse() }

    override suspend fun getById(id: Int): UserResponse? {
        return userRepository.getById(id)?.toResponse()
    }

    override suspend fun getByEmail(email: String): UserResponse? {
        return userRepository.getByEmail(email)?.toResponse()
    }

    override suspend fun getByPhone(phone: String): UserResponse? {
        return userRepository.getByPhone(phone)?.toResponse()
    }

    override suspend fun create(request: CreateUserRequest): Int {
        val user = User(
            id = 0,
            email = request.email,
            phone = request.phone,
            name = request.name,
            avatarUrl = request.avatarUrl,
            role = UserRole.fromId(request.roleId),
            bonusBalance = 0,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        val passwordHash = passwordService.hash(request.password)
        return userRepository.create(user, passwordHash)
    }

    override suspend fun update(
        id: Int,
        request: UpdateUserRequest
    ): Boolean {
        val existing = userRepository.getById(id) ?: return false

        val role = UserRole.fromId(request.roleId ?: -1)

        val updated = existing.copy(
            email = request.email ?: existing.email,
            phone = request.phone ?: existing.phone,
            name = request.name ?: existing.name,
            avatarUrl = request.avatarUrl ?: existing.avatarUrl,
            role = if (role.id > 0) role else existing.role,
            bonusBalance = request.bonusBalance ?: existing.bonusBalance,
            updatedAt = LocalDateTime.now()
        )
        return userRepository.update(updated)
    }

    override suspend fun delete(id: Int): Boolean {
        return userRepository.delete(id)
    }
}