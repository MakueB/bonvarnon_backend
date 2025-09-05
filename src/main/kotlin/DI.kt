package ru.makiev

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import ru.makiev.di.dataModule
import ru.makiev.di.repositoryModule
import ru.makiev.di.serviceModule

fun Application.configureDi() {
    install(Koin) {
        modules(dataModule, repositoryModule, serviceModule)
    }
}