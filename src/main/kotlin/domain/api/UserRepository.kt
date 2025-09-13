package ru.makiev.domain.api

import ru.makiev.domain.model.User

interface UserRepository {
    suspend fun getAll(): List<User>
    suspend fun findById(id: Int): User?
    suspend fun findByEmail(email: String): User?
    suspend fun findByPhone(phone: String): User?
    suspend fun create(user: User, passwordHash: String): Int
    suspend fun update(user: User): Boolean
    suspend fun delete(id: Int): Boolean
}