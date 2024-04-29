package ru.alex3koval.ktorAuthentication.server

import io.ktor.server.application.*
import ru.alex3koval.ktorAuthentication.server.plugins.configureRouting
import ru.alex3koval.ktorAuthentication.server.plugins.configureSecurity
import ru.alex3koval.ktorAuthentication.server.plugins.configureSerialization


fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureRouting()
}
