package ru.makiev.domain.api.repository

import ru.makiev.domain.model.User

interface UserRepository {
    suspend fun getAll(): List<User>
    suspend fun getById(id: Int): User?
    suspend fun getByEmail(email: String): User?
    suspend fun getByPhone(phone: String): User?
    suspend fun create(user: User, passwordHash: String): Int
    suspend fun update(user: User): Boolean
    suspend fun delete(id: Int): Boolean
}