package ru.alex3koval.ktorAuthentication.server.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureSerialization() {
    install(ContentNegotiation)
}
