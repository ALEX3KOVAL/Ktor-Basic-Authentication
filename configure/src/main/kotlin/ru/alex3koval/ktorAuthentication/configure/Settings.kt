package ru.alex3koval.ktorAuthentication.configure

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString

/**
 * Конфигурация приложения
 */
@Serializable
class Settings internal constructor(
    val mainDB: MainDB,
    val securitySecretKey: SecuritySecretKey
) {
    companion object {
        fun read(): Settings {
            val configFromFile =
                Settings::class.java.getResourceAsStream("/global.yaml")!!.bufferedReader().use { it.readText() }

            return Yaml.default.decodeFromString<Settings>(configFromFile)
        }
    }

    /**
     * Конфигурационные данные БД
     *
     * @property jdbcURL URL подключения
     * @property login Логин пользователя
     * @param password Пароль пользователя
     * @property dbName Наименование БД
     * @property dbHost Хост БД
     * @property dbPort Порт хоста
     * @property maximumPoolSize Максимальное количество одновременно открытых подключений
     */
    @Serializable
    data class MainDB(
        val jdbcURL: String,
        val login: String,
        val password: String,
        val dbName: String,
        val dbHost: String,
        val dbPort: String,
        val maximumPoolSize: Int
    )

    /**
     * Приватный ключ для хэширования информации
     */
    @Serializable
    data class SecuritySecretKey(val value: String)
}
