package ru.alex3koval.ktorAuthentication.administration.vo

import ru.alex3koval.ktorAuthentication.administration.core.Checker.Companion.message
import ru.alex3koval.ktorAuthentication.administration.core.fail
import ru.alex3koval.ktorAuthentication.administration.core.failIf

/**
 * ФИО - Составной VO
 */
data class Fio(val name: Name, val surname: Surname, val patronymic: Patronymic? = null) {
    override fun toString(): String = "${surname.value} ${name.value} ${patronymic?.value ?: ""}".trim()

    companion object {
        /**
         * Распознать ФИО из строки
         *
         * Функция распознаёт ФИО любого из следующих форматов:
         * Фамилия Имя Отчество
         * Фамилия Имя
         *
         * Функция успешно распознаёт строку только в случае
         * полной уверенности в однозначности распознавания.
         * В остальных случаях результат будет не успешен и необходимо
         * использовать конструктор класса Fio
         *
         * @param rawValue строка для распознавания
         *
         * @return результат распознавания ФИО
         */
        operator fun invoke(rawValue: String): Result<Fio> = runCatching {
            val words = rawValue.trim().split(Regex(" +"))

            val isPatronymic: (String) -> Boolean = {
                it.endsWith("ич", ignoreCase = true) || it.endsWith("на", ignoreCase = true)
            }

            when {
                words.size < 2 -> throw RuntimeException("Не удалось распознать ФИО: $rawValue")
                words.size == 2 -> Fio(
                    surname = Surname(words[0]).getOrThrow(),
                    name = Name(words[1]).getOrThrow(),
                    patronymic = null
                )
                words.size == 3 && isPatronymic(words[2]) -> Fio(
                    surname = Surname(words[0]).getOrThrow(),
                    name = Name(words[1]).getOrThrow(),
                    patronymic = Patronymic(words[2]).getOrThrow()
                )
                else -> throw RuntimeException("Не удалось однозначно распознать ФИО: $rawValue")
            }
        }
    }

    /**
     * Имя
     */
    class Name private constructor(val value: String) {
        init {
            failIf { value.isBlank() } message "Имя не может быть пустым"
            failIf { value.length !in 2..50 } message "Некорректная длина имени: $value"

            value.split(" ")
                .firstOrNull { !it.matches(Regex("^([A-ЯЁ][а-яё]{1,49}+${'$'})|([A-ЯЁ][а-яё]{1,49}-[A-ЯЁ][а-яё]{1,49}+${'$'})")) }
                ?.let { fail("Некорректный формат имени: $value") }
        }

        companion object {
            operator fun invoke(value: String): Result<Name> {
                return Result.runCatching {
                    Name(value.trim())
                }
            }
        }

        override fun toString() = value
    }

    /**
     * Фамилия
     */
    class Surname private constructor(val value: String) {
        override fun toString(): String {
            return value
        }

        init {
            failIf { value.isBlank() } message "Фамилия не может быть пустой"
            failIf { value.length !in 2..50 } message "Некорректная длина фамилии: $value"

            value.split(" ")
                .firstOrNull { !it.matches(Regex("^([A-ЯЁ][а-яё]{1,49}+${'$'})|([A-ЯЁ][а-яё]{1,49}-[A-ЯЁ][а-яё]{1,49}+${'$'})")) }
                ?.let { fail("Некорректный формат фамилии: $value") }
        }

        companion object {
            operator fun invoke(value: String): Result<Surname> = runCatching { Surname(value.trim()) }
        }
    }

    /**
     * Отчество
     */
    class Patronymic private constructor(val value: String) {
        override fun toString(): String {
            return value
        }

        init {
            failIf { value.isBlank() } message "Отчество не может быть пустым"
            failIf { value.length !in 2..50 } message "Некорректная длина отчества: $value"

            value.split(" ")
                .firstOrNull { !it.matches(Regex("^([A-ЯЁ][а-яё]{1,49}+${'$'})|([A-ЯЁ][а-яё]{1,49}-[A-ЯЁ][а-яё]{1,49}+${'$'})")) }
                ?.let { fail("Некорректный формат отчества: $value") }
        }

        companion object {
            operator fun invoke(value: String): Result<Patronymic> = runCatching { Patronymic(value.trim()) }
        }
    }
}
