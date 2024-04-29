package ru.alex3koval.ktorAuthentication.administration.repository

import org.jetbrains.exposed.sql.*

/**
 * Выбрать одну найденную запись по заданному условию
 *
 * @param where Условие
 */
fun FieldSet.singleSelect(
    where: SqlExpressionBuilder.() -> Op<Boolean>
): Query = select(where).limit(1)
