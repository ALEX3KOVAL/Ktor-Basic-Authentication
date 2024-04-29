package ru.alex3koval.ktorAuthentication.administration.core

/**
 * Валидатор, который поднимает исключение, если удовлетворено преданное условие
 */
class Checker(private val condition: () -> Boolean) {
    /**
     * Поднять исключение, если переданное условие удовлетворено
     *
     * @param message Сообщение исключения
     */
    private fun check(message: String) {
        if (condition()) {
            throw RuntimeException(message)
        }
    }

    companion object {
        infix fun Checker.message(message: String) {
            this.check(message)
        }
    }
}

/**
 * Поднять исключение, если переданное условие удовлетворено
 *
 * @param condition Замыкание с условием
 * @throws RuntimeException
 */
fun failIf(condition: () -> Boolean): Checker = Checker(condition)


/**
 * Поднять исключение
 *
 * @throws RuntimeException
 */
fun fail(message: String): Nothing {
    throw RuntimeException(message)
}
