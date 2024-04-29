package ru.alex3koval.ktorAuthentication.administration.vo

/**
 * Пол
 */
enum class Gender(val value: Int) {

    /** Неизвестно */
    UNKNOWN(0) {
        override fun toString() = "неизвестно"
    },

    /** Мужской пол */
    MALE(1) {
        override fun toString() = "мужской"
    },

    /** Женский пол */
    FEMALE(2) {
        override fun toString() = "женский"
    },

    /** Неприменимо */
    NOT_APPLICABLE(9) {
        override fun toString() = "неприменимо"
    };

    companion object {
        operator fun invoke(value: Int): Result<Gender> =
            entries
                .firstOrNull { it.value == value }
                ?.let { Result.success(it) }
                ?: Result.failure(RuntimeException("Некорректный код гендера: $value"))
    }
}