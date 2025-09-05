package ru.makiev.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.makiev.data.mapper.toMenuCategoryResponse
import ru.makiev.domain.api.MenuService

fun Application.configureMenuRoutes() {
    val menuService: MenuService by inject()
    routing {
        route("/menu") {
            get("/full") {
                val categories = menuService.getFullMenu()
                val response = categories.map { category ->
                    category.toMenuCategoryResponse()
                }
                call.respond(response)
            }
        }
    }
}