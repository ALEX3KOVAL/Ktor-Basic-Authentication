package ru.alex3koval.ktorAuthentication.administration.vo

import ru.alex3koval.ktorAuthentication.administration.core.Checker.Companion.message
import ru.alex3koval.ktorAuthentication.administration.core.failIf

/**
 * Email
 */
class Email private constructor(val value: String) {
    init {
        failIf { value.isEmpty() } message  "Email не может быть пустым"
        failIf { !value.matches("^[a-zA-Z0-9.!#\$%&\'*+=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+$".toRegex()) } message "Недопустимые символы в e-mail адресе: $value"
    }

    override fun toString(): String = value

    override fun equals(other: Any?): Boolean = other is Email && other.value == value

    override fun hashCode(): Int = value.hashCode()

    companion object {
        operator fun invoke(value: String): Result<Email> = runCatching {
            val clean = value.trim().dropLastWhile { !it.toString().matches("^[A-Za-z]+$".toRegex()) }.lowercase()

            Email(clean)
        }
    }
}