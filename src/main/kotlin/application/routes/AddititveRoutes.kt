package ru.makiev.application.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.makiev.application.dto.CreateAdditiveRequest
import ru.makiev.application.dto.UpdateAdditiveRequest
import ru.makiev.application.exception.ValidationException
import ru.makiev.application.mapper.toAdditiveResponse
import ru.makiev.domain.api.AdditiveService
import java.math.BigDecimal

fun Application.configureAdditiveRoutes() {
    val additiveService: AdditiveService by inject()

    routing {
        route("/additives"){
            get {
                val additives = additiveService.getAll()
                call.respond(additives.map { it.toAdditiveResponse() })
            }

            get ("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid ID")

                val additive = additiveService.getById(id)
                    ?: return@get call.respond(HttpStatusCode.NotFound, "Additive not found")

                call.respond(additive.toAdditiveResponse())
            }

            post {
                val request = call.receive<CreateAdditiveRequest>()
                val errors = request.validate()
                if (errors.isNotEmpty()) throw ValidationException(errors)

                val created = additiveService.create(request)
                call.respond(HttpStatusCode.Created, created.toAdditiveResponse())
            }

            patch("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@patch call.respond(HttpStatusCode.BadRequest, "Invalid ID")

                val request = call.receive<UpdateAdditiveRequest>()
                request.extraPrice?.let {
                    if (it.toBigDecimal() <= BigDecimal.ZERO) return@patch call.respond("Price must be a valid number")
                }
                val updated = additiveService.update(id, request)

                if (updated != null) {
                    call.respond(updated.toAdditiveResponse())
                } else {
                    call.respond(HttpStatusCode.NotFound, "Additive not found")
                }
            }
        }
    }
}