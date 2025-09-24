package ru.makiev.application.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.makiev.application.dto.size.CreateSizeRequest
import ru.makiev.application.dto.size.UpdateSizeRequest
import ru.makiev.application.exception.ValidationException
import ru.makiev.domain.api.service.SizeService

fun Application.configureSizes(){
    val sizeService: SizeService by inject()

    routing {
        route("/sizes") {
            get {
                call.respond(sizeService.getAll())
            }

            get("{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                val size = sizeService.getById(id)
                    ?: return@get call.respond(HttpStatusCode.NotFound, "Size not found")
                call.respond(size)
            }

            post {
                val request = call.receive<CreateSizeRequest>()
                val errors = request.validate()
                if (errors.isNotEmpty()) throw ValidationException(errors)

                val size = sizeService.create(request)
                call.respond(HttpStatusCode.Created, size)
            }

            patch("{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@patch call.respond(HttpStatusCode.BadRequest, "Invalid size ID")

                val request = call.receive<UpdateSizeRequest>()
                val updated = sizeService.update(id, request)
                if (updated) {
                    call.respond(HttpStatusCode.OK, "Size updated successfully")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Size not found")
                }
            }

            delete("{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@delete call.respond(HttpStatusCode.BadRequest, "Invalid size ID")
                val deleted = sizeService.delete(id)
                if (deleted) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Size not found")
                }
            }
        }
    }
}
