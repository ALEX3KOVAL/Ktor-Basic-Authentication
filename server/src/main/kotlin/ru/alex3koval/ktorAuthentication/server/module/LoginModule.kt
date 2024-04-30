package ru.alex3koval.ktorAuthentication.server.module

import io.ktor.server.application.call
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.freemarker.FreeMarkerContent
import io.ktor.server.response.respond
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.sessions.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.alex3koval.ktorAuthentication.administration.ClientRDTO
import ru.alex3koval.ktorAuthentication.server.security.SecurityService
import ru.alex3koval.ktorAuthentication.administration.repository.ClientRepository
import ru.alex3koval.ktorAuthentication.server.ClientSession
import ru.alex3koval.ktorAuthentication.server.core.innerTransacted
import ru.alex3koval.ktorAuthentication.server.koin


/**
 * Маршрутизация эндпоинтов авторизации
 */
fun Route.configureLoginModule() {
    get("/login") {
        val clientRepository = call.koin.get<ClientRepository>()
        val securityService = call.koin.get<SecurityService>()

        call.sessions.get<ClientSession>()?.let { session ->
            transaction { clientRepository.get(login = session.name) }?.let { client ->
                // если у сотрудника есть сессия, он активный и токен у него корректный, то редиректим на страницу кредитора
                if (securityService.checkToken(client = client, token = session.token) && client.activity.isActive()) {
                    // todo далее изменить адрес
                    call.respondRedirect(url = "/")
                }
            }
        } ?: call.respond(message = FreeMarkerContent("login.ftl", emptyMap<String, String>()))
    }

    innerTransacted {
        get("/logout") {
            call.sessions.clear<ClientSession>()
            call.respondRedirect("/login")
        }
    }

    authenticate("auth-form") {
        post("/login") {
            val clientRepository = call.koin.get<ClientRepository>()
            val securityService = call.koin.get<SecurityService>()

            val userName = call.principal<UserIdPrincipal>()?.name.toString()

            try {
                val user: ClientRDTO = transaction {
                    clientRepository.get(userName)!!
                }
                call.sessions.set(ClientSession(name = userName, securityService.getToken(user)))
                // todo далее изменить адрес
                call.respondRedirect(url = "/")
            } catch (e: Exception) {
                call.respondRedirect("/login")
            }
        }
    }
}
