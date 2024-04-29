package ru.alex3koval.ktorAuthentication.server.vo

/**
 * Пароль
 */
data class Password(val value: String) {
    override fun toString(): String = value
}
