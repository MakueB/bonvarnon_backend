package ru.makiev.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ru.makiev.data.mapper.toMenuCategoryResponse
import ru.makiev.data.mapper.toMenuItemResponse
import ru.makiev.data.model.CreateMenuCategoryRequest
import ru.makiev.data.model.CreateMenuItemRequest
import ru.makiev.data.model.UpdateMenuCategoryRequest
import ru.makiev.data.model.UpdateMenuItemRequest
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

            post("/categories") {
                val request = call.receive<CreateMenuCategoryRequest>()
                val created = menuService.createCategory(request)
                call.respond(HttpStatusCode.Created, created.toMenuCategoryResponse())
            }

            put("/categories/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?:return@put call.respond(HttpStatusCode.BadRequest, "Invalid category id")

                val request = call.receive<UpdateMenuCategoryRequest>()
                val updated = menuService.updateCategory(id, request)

                if (updated != null) {
                    call.respond(updated.toMenuCategoryResponse())
                } else {
                    call.respond(HttpStatusCode.NotFound, "Category not found")
                }
            }

            delete("/categories/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@delete call.respond(HttpStatusCode.BadRequest, "invalid category id")

                val deleted = menuService.deleteCategory(id)
                if (deleted) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Category not found")
                }
            }

            post("/items") {
                val request = call.receive<CreateMenuItemRequest>()
                val created = menuService.createItem(request)
                call.respond(HttpStatusCode.Created, created.toMenuItemResponse())
            }

            put("/items/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@put call.respond(HttpStatusCode.BadRequest, "Invalid menu item id")

                val request = call.receive<UpdateMenuItemRequest>()
                val updated = menuService.updateItem(id, request)

                if (updated != null) {
                    call.respond(updated.toMenuItemResponse())
                } else {
                    call.respond(HttpStatusCode.NotFound, "Menu item not found")
                }
            }

            delete("/items/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@delete call.respond(HttpStatusCode.BadRequest, "invalid menu item id")

                val deleted = menuService.deleteItem(id)
                if (deleted) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Menu item not found")
                }
            }
        }
    }
}