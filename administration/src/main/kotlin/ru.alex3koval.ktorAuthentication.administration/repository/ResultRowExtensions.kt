package ru.alex3koval.ktorAuthentication.administration.repository

import org.jetbrains.exposed.sql.ResultRow
import ru.alex3koval.ktorAuthentication.administration.repository.entity.ClientTable
import ru.alex3koval.ktorAuthentication.administration.vo.Fio

/** ФИО */
inline val ResultRow.fio: Fio
    get() = Fio(
        surname = Fio.Surname(get(ClientTable.surname)).getOrThrow(),
        name = Fio.Name(get(ClientTable.name)).getOrThrow(),
        patronymic = getOrNull(ClientTable.patronymic)?.let { Fio.Patronymic(it).getOrThrow() }
    )
