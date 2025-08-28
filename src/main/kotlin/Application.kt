package ru.makiev

import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureDatabases()
    //configureSockets()
    //configureSecurity()
    //configureHTTP()
    //configureMonitoring()
    //configureFrameworks()
    configureRouting()
}
