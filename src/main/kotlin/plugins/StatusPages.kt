package ru.makiev.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.autohead.AutoHeadResponse
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.makiev.application.exception.ValidationException

fun Application.configureStatusPages() {
    install(AutoHeadResponse)
    install(StatusPages) {
        exception<ValidationException> { call, cause ->
            call.respond(
                HttpStatusCode.BadRequest,
                mapOf("error" to "Validation failed", "details" to cause.errors)
            )
        }

        status(HttpStatusCode.BadRequest) { call, status ->
            call.respond(status, mapOf("error" to "Bad request"))
        }

        status(HttpStatusCode.NotFound) { call, status ->
            call.respond(status, mapOf("error" to "Resource not found"))
        }

        exception<ExposedSQLException> { call, cause ->
            call.respond(HttpStatusCode.Conflict, mapOf("error" to "Conflict: ${cause.message}"))
        }

        exception<Throwable> { call, cause ->
            call.application.environment.log.error("Unhandled error", cause)
            call.respond(HttpStatusCode.InternalServerError, mapOf("error" to "Internal server error"))
        }
    }
}