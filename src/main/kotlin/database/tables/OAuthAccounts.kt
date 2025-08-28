package ru.makiev.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime

object OAuthAccounts : IntIdTable("oauth_accounts") {
    val userId = integer("user_id").references(Users.id)
    val provider = text("provider")
    val providerUserId = text("provider_user_id")
    val accessToken = text("access_token").nullable()
    val refreshToken = text("refresh_token").nullable()
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}