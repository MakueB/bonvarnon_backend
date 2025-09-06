package ru.makiev.plugins

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import ru.makiev.infrastructure.config.di.dataModule
import ru.makiev.infrastructure.config.di.repositoryModule
import ru.makiev.infrastructure.config.di.serviceModule

fun Application.configureDi() {
    install(Koin) {
        modules(dataModule, repositoryModule, serviceModule)
    }
}