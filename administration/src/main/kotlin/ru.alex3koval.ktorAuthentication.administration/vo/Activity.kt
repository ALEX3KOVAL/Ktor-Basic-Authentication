package ru.alex3koval.ktorAuthentication.administration.vo

/**
 * Состояние активности аккаунта пользователя (активен/неактивен)
 */
data class Activity(private val value: Boolean) {
    override fun toString(): String {
        return if (value) "Пользователь активен" else "Пользователь заблокирован"
    }

    fun isActive() = value
}
