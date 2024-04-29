package ru.alex3koval.ktorAuthentication.administration

import ru.alex3koval.ktorAuthentication.administration.vo.*

/**
 * RDTO пользователя
 *
 * @property id ID пользователя
 * @property login Логин
 * @property password Пароль
 * @property salt Соль
 * @property fio ФИО
 * @property activity Активность пользователя
 * @property gender Пол
 * @property email Электронная почта
 */
data class ClientRDTO(
    val id: ClientID,
    val login: String,
    val password: String,
    val salt: String,
    val fio: Fio,
    val activity: Activity,
    val gender: Gender,
    val email: Email
)
