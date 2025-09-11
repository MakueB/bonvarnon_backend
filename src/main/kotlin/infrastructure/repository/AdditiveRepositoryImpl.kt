package ru.makiev.infrastructure.repository

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import ru.makiev.application.dto.additive.CreateAdditiveRequest
import ru.makiev.application.dto.additive.UpdateAdditiveRequest
import ru.makiev.data.database.tables.Additives
import ru.makiev.data.database.tables.MenuItems
import ru.makiev.domain.api.AdditiveRepository
import ru.makiev.domain.model.Additive
import ru.makiev.plugins.dbQuery

class AdditiveRepositoryImpl : AdditiveRepository {
    override suspend fun getAll(): List<Additive> {
        return dbQuery {
            Additives.selectAll().map { row ->
                Additive(
                    id = row[Additives.id].value,
                    name = row[Additives.name],
                    extraPrice = row[Additives.extraPrice]
                )
            }
        }
    }

    override suspend fun getById(id: Int): Additive? {
        return dbQuery {
            Additives.selectAll()
                .where { Additives.id eq id }
                .map { row ->
                    Additive(
                        id = row[Additives.id].value,
                        name = row[Additives.name],
                        extraPrice = row[Additives.extraPrice]
                    )
                }.singleOrNull()
        }
    }

    override suspend fun create(request: CreateAdditiveRequest): Additive {
        return dbQuery {
            val id = Additives.insertAndGetId { row ->
                row[Additives.name] = request.name
                row[Additives.extraPrice] = request.extraPrice.toBigDecimal()
            }.value

            Additive(
                id = id,
                name = request.name,
                extraPrice = request.extraPrice.toBigDecimal()
            )
        }
    }

    override suspend fun update(
        id: Int,
        request: UpdateAdditiveRequest
    ): Additive? {
        return dbQuery {
            val updated = Additives.update({ Additives.id eq id }) { row ->
                request.name?.let { row[name] = it }
                request.extraPrice?.let { row[extraPrice] = it.toBigDecimal() }
            }
            if (updated > 0) {
                Additives.selectAll()
                    .where { MenuItems.id eq id }
                    .map { row ->
                        Additive(
                            id = row[Additives.id].value,
                            name = row[Additives.name],
                            extraPrice = row[Additives.extraPrice]
                        )
                    }.singleOrNull()
            } else null
        }
    }

    override suspend fun delete(id: Int): Boolean {
        return dbQuery { Additives.deleteWhere { Additives.id eq id } > 0 }
    }
}