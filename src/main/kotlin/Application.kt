package ru.makiev

import io.ktor.server.application.*
import ru.makiev.application.routes.configureAdditiveRoutes
import ru.makiev.application.routes.configureMenuRoutes
import ru.makiev.plugins.configureDatabases
import ru.makiev.plugins.configureDi
import ru.makiev.plugins.configureMonitoring
import ru.makiev.plugins.configureSerialization
import ru.makiev.plugins.configureStatusPages

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureDatabases()
    configureDi()
    configureStatusPages()
    configureAdditiveRoutes()
    //configureSockets()
    //configureSecurity()
    //configureHTTP()
    configureMonitoring()
    //configureFrameworks()
    configureMenuRoutes()
}
