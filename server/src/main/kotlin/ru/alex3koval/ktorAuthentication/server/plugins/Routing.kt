package ru.alex3koval.ktorAuthentication.server.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.alex3koval.ktorAuthentication.server.module.configureLoginModule


/**
 * Модуль маршрутизации
 */
fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondRedirect("/login")
        }

        configureLoginModule()

        staticResources("static", "static")
    }
}
