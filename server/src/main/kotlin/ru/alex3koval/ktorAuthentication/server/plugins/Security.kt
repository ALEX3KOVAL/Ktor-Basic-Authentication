package ru.alex3koval.ktorAuthentication.server.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.alex3koval.ktorAuthentication.administration.ClientRDTO
import ru.alex3koval.ktorAuthentication.administration.repository.ClientRepository
import ru.alex3koval.ktorAuthentication.server.factory.DIBuilder
import ru.alex3koval.ktorAuthentication.server.security.SecurityService
import ru.alex3koval.ktorAuthentication.server.vo.Password


/**
 * Модуль защиты конфиденциальных данных
 */
fun Application.configureSecurity() {
    val clientRepository = DIBuilder.di.get<ClientRepository>()
    val securityService = DIBuilder.di.get<SecurityService>()

    data class ClientSession(val name: String, val token: String) : Principal

    install(Sessions) {
        cookie<ClientSession>("client_session") {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 60 * 60 * 24 * 7
        }
    }

    install(Authentication) {
        session<ClientSession>("auth-session") {
            validate { session ->
                try {
                    val client: ClientRDTO =
                        transaction {
                            clientRepository.get(session.name)
                        } ?: return@validate null

                    if (securityService.checkToken(client, session.token)) {
                        if (!client.activity.isActive()) {
                            return@validate null
                        }

                        session
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    null
                }
            }

            challenge {
                call.respondRedirect("/login")
            }
        }

        form(name = "auth-form") {
            userParamName = "username"
            passwordParamName = "password"

            validate { credentials ->
                val client: ClientRDTO = try {
                    transaction {
                        clientRepository.get(credentials.name)
                    } ?: return@validate null
                } catch (_: Exception) {
                    return@validate null
                }
                if (securityService.checkUserCredential(client, Password(credentials.password))) {
                    UserIdPrincipal(client.login)
                } else {
                    null
                }
            }

            challenge {
                call.respond(
                    FreeMarkerContent(
                        "login.ftl",
                        mapOf(
                            "username" to (it?.name ?: ""),
                            "password" to (it?.password ?: ""),
                            "error" to "Неверное имя пользователя или пароль"
                        )
                    )
                )
            }
        }
    }
}
