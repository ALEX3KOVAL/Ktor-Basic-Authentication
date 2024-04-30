package ru.alex3koval.ktorAuthentication.server.core

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

/**
 * Обертка для внутренних обработчиков роутов:
 *  1. Добавляет ко всем роутам объявленным внутри build аутентификацию
 *  2. Оборачивает обработчик роута в транзакцию
 */
fun Route.innerTransacted(build: Route.() -> Unit): Route {
    val route = authenticate("auth-session", build = build)

    route.children.forEach {
        it.intercept(ApplicationCallPipeline.Plugins) {
            newSuspendedTransaction {
                proceed()
            }
        }
    }

    return route
}