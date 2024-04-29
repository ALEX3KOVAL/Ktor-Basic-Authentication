package ru.alex3koval.ktorAuthentication.administration.repository

import org.jetbrains.exposed.sql.ResultRow
import ru.alex3koval.ktorAuthentication.administration.ClientRDTO
import ru.alex3koval.ktorAuthentication.administration.repository.entity.ClientTable
import ru.alex3koval.ktorAuthentication.administration.vo.ClientID


/**
 * Репозиторий пользователя
 */
class ClientRepository {
    /**
     * Получить пользователя по ID
     *
     * @param id ID пользователя
     */
    fun get(id: ClientID): ClientRDTO? =
        ClientTable
            .singleSelect { ClientTable.id eq id.value }
            .singleOrNull()
            ?.rdto

    /**
     * Получить RDTO по логину пользователя
     */
    fun get(login: String): ClientRDTO? =
        ClientTable
            .singleSelect { ClientTable.login eq login }
            .singleOrNull()
            ?.rdto

    /** RDTO из результата запроса */
    private val ResultRow.rdto: ClientRDTO
        get() = ClientRDTO(
            id = get(ClientTable.id).id(ClientID::class),
            login = get(ClientTable.login),
            password = get(ClientTable.password),
            salt = get(ClientTable.salt),
            fio = fio,
            activity = get(ClientTable.activity).activity,
            gender = get(ClientTable.gender).gender,
            email = get(ClientTable.email).email
        )
}
