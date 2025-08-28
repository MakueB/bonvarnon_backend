package ru.makiev

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction


fun Application.configureDatabases() {
    val dbConfig = environment.config.config("ktor.database")

    val hikariConfig = HikariConfig().apply {
        jdbcUrl = dbConfig.property("url").getString()
        username = dbConfig.property("user").getString()
        password = dbConfig.property("password").getString()
        driverClassName = dbConfig.property("driver").getString()
        maximumPoolSize = dbConfig.property("maximumPoolSize").getString().toInt()
        connectionTimeout = dbConfig.property("connectionTimeout").getString().toLong()
        idleTimeout = dbConfig.property("idleTimeout").getString().toLong()
        maxLifetime = dbConfig.property("maxLifetime").getString().toLong()
    }

    val dataSource = HikariDataSource(hikariConfig)

    Flyway.configure()
        .dataSource(dataSource)
        .connectRetries(3)
        .locations("classpath:db/migration")
        .loggers("slf4j")
        .load()
        .migrate()

    Database.connect(dataSource)
}

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }
