package ru.alex3koval.ktorAuthentication.administration.repository.entity

import org.jetbrains.exposed.dao.id.IntIdTable

/**
 * Представление таблицы `client` (пользователь)
 */
object ClientTable : IntIdTable("client") {
    val login = varchar("login", 255)
    val password = varchar("password", 255)
    val salt = varchar("salt", 255)
    val name = varchar("name", 255)
    val surname = varchar("surname", 255)
    val patronymic = varchar("surname", 255).nullable()
    val gender = integer("gender")
    val email = varchar("email", 255)
    val activity = bool("activity")
}
