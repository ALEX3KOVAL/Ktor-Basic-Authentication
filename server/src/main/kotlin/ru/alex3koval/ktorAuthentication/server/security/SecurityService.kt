package ru.alex3koval.ktorAuthentication.server.security

import ru.alex3koval.ktorAuthentication.administration.ClientRDTO
import ru.alex3koval.ktorAuthentication.server.vo.Password
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

/**
 * Сервис защиты конфиденциальных данных
 */
class SecurityService(private val securitySecretKey: String) {
    /**
     * Сгенерировать хэш по алгоритму "MD5"
     */
    private fun md5(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(StandardCharsets.UTF_8))

    /**
     * Привести массив байтов к HEX-строке
     */
    private fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

    /**
     * Проверить введённый пароль клиента
     *
     * @param client RDTO клиента
     * @param password Незахешированный пароль клиента
     */
    fun checkUserCredential(client: ClientRDTO, password: Password): Boolean =
        client.password == md5("$password${client.salt}").toHex()

    /**
     * Получить токен доступа сотрудника
     *
     * @param staff RDTO сотрудника
     */
    private fun getToken(staff: ClientRDTO): String = md5("${staff.login}${staff.password}$securitySecretKey").toHex()

    /**
     * Сравнить токен доступа пользователя, который пришёл с клиента
     *
     * @param client RDTO пользователя
     * @param token Токен доступа с клиента
     */
    fun checkToken(client: ClientRDTO, token: String): Boolean = getToken(client) == token
}
