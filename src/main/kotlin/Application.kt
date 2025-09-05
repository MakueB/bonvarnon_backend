package ru.makiev

import io.ktor.server.application.*
import ru.makiev.routes.configureMenuRoutes
import ru.makiev.routes.configureRouting

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureDatabases()
    configureDi()
    //configureSockets()
    //configureSecurity()
    //configureHTTP()
    configureMonitoring()
    //configureFrameworks()
    configureRouting()
    configureMenuRoutes()
}
