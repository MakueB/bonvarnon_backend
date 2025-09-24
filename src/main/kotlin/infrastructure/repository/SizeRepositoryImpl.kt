package ru.makiev.infrastructure.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import ru.makiev.data.database.tables.Sizes
import ru.makiev.domain.api.repository.SizeRepository
import ru.makiev.domain.model.Size
import ru.makiev.plugins.dbQuery

class SizeRepositoryImpl : SizeRepository {
    override suspend fun getById(id: Int): Size? {
        return dbQuery {
            Sizes.selectAll()
                .where { Sizes.id eq id }
                .map { row -> row.toSize() }
                .singleOrNull()
        }
    }

    override suspend fun getAll(): List<Size> {
        return dbQuery {
            Sizes.selectAll().map { row -> row.toSize() }
        }
    }

    override suspend fun create(size: Size): Int {
        return dbQuery {
            Sizes.insertAndGetId { row ->
                row[Sizes.id] = size.id
                row[Sizes.name] = size.name
                row[Sizes.extraPrice] = size.extraPrice
                row[Sizes.createdAt] = size.createdAt
                row[Sizes.updatedAt] = size.updatedAt
            }.value
        }
    }

    override suspend fun update(size: Size): Boolean {
        return dbQuery {
            Sizes.update({ Sizes.id eq size.id }) { row ->
                row[Sizes.name] = size.name
                row[Sizes.extraPrice] = size.extraPrice
                row[Sizes.updatedAt] = size.updatedAt
            } > 0
        }
    }

    override suspend fun delete(id: Int): Boolean {
        return Sizes.deleteWhere { Sizes.id eq id } > 0
    }

    private fun ResultRow.toSize(): Size {
        return Size(
            id = this[Sizes.id].value,
            name = this[Sizes.name],
            extraPrice = this[Sizes.extraPrice],
            createdAt = this[Sizes.createdAt],
            updatedAt = this[Sizes.updatedAt]
        )
    }
}