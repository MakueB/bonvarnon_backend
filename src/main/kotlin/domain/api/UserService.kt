package ru.makiev.domain.api

import ru.makiev.application.dto.user.CreateUserRequest
import ru.makiev.application.dto.user.UpdateUserRequest
import ru.makiev.application.dto.user.UserResponse

interface UserService {
    suspend fun getAll(): List<UserResponse>
    suspend fun getById(id: Int): UserResponse?
    suspend fun getByEmail(email: String): UserResponse?
    suspend fun getByPhone(phone: String): UserResponse?
    suspend fun create(request: CreateUserRequest): Int
    suspend fun update(id: Int, request: UpdateUserRequest): Boolean
    suspend fun delete(id: Int): Boolean
}