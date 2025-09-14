package ru.makiev.infrastructure.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import ru.makiev.data.database.tables.Users
import ru.makiev.domain.api.UserRepository
import ru.makiev.domain.model.User
import ru.makiev.domain.model.UserRole
import ru.makiev.plugins.dbQuery

class UserRepositoryImpl : UserRepository {
    override suspend fun getAll(): List<User> {
        return dbQuery {
            Users.selectAll().map { row ->
                User(
                    id = row[Users.id].value,
                    email = row[Users.email],
                    phone = row[Users.phone],
                    name = row[Users.name],
                    avatarUrl = row[Users.avatarUrl],
                    role = UserRole.fromId(row[Users.roleId]),
                    bonusBalance = row[Users.bonusBalance],
                    createdAt = row[Users.createdAt],
                    updatedAt = row[Users.updatedAt]
                )
            }
        }
    }

    override suspend fun getById(id: Int): User? {
        return dbQuery {
            Users.selectAll()
                .where { Users.id eq id }
                .map { row -> rowToUser(row) }
        }.singleOrNull()
    }

    override suspend fun getByEmail(email: String): User? {
        return dbQuery {
            Users.selectAll()
                .where { Users.email eq email }
                .map { row -> rowToUser(row) }
        }.singleOrNull()
    }

    override suspend fun getByPhone(phone: String): User? {
        return dbQuery {
            Users.selectAll().where { Users.phone eq phone }.map { rowToUser(it) }
        }.singleOrNull()
    }

    override suspend fun create(user: User, passwordHash: String): Int {
        return dbQuery {
            Users.insertAndGetId { row ->
                row[Users.email] = user.email
                row[Users.phone] = user.phone
                row[Users.passwordHash] = passwordHash
                row[Users.name] = user.name
                row[Users.avatarUrl] = user.avatarUrl
                row[Users.roleId] = user.role.id
                row[Users.bonusBalance] = user.bonusBalance
                row[Users.createdAt] = user.createdAt
                row[Users.updatedAt] = user.updatedAt
            }.value
        }
    }

    override suspend fun update(user: User): Boolean {
        return dbQuery {
            val updated = Users.update({ Users.id eq user.id }) { row ->
                row[Users.email] = user.email
                row[Users.phone] = user.phone
                row[Users.name] = user.name
                row[Users.avatarUrl] = user.avatarUrl
                row[Users.roleId] = user.role.id
                row[Users.bonusBalance] = user.bonusBalance
                row[Users.updatedAt] = user.updatedAt
            }

            updated > 0
        }
    }

    override suspend fun delete(id: Int): Boolean {
        return dbQuery {
            val deleted = Users.deleteWhere{ Users.id eq id }
            deleted > 0
        }
    }

    private fun rowToUser(row: ResultRow): User = User(
        id = row[Users.id].value,
        email = row[Users.email],
        phone = row[Users.phone],
        name = row[Users.name],
        avatarUrl = row[Users.avatarUrl],
        role = UserRole.fromId(row[Users.roleId]),
        bonusBalance = row[Users.bonusBalance],
        createdAt = row[Users.createdAt],
        updatedAt = row[Users.updatedAt]
    )
}