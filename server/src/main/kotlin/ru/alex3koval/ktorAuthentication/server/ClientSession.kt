package ru.alex3koval.ktorAuthentication.server

import io.ktor.server.auth.Principal

/**
 * Данные сессии пользователя
 *
 * @property name Имя пользователя
 * @property token Токен доступа пользователя
 */
data class ClientSession(val name: String, val token: String) : Principal