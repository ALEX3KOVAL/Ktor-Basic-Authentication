package ru.alex3koval.ktorAuthentication.administration.repository

import org.jetbrains.exposed.dao.id.EntityID
import ru.alex3koval.ktorAuthentication.administration.vo.Activity
import ru.alex3koval.ktorAuthentication.administration.vo.Email
import ru.alex3koval.ktorAuthentication.administration.vo.Gender
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

/**
 * VO ID из PK таблицы
 */
fun<T: EntityID<*>, R : Any> T.id(clazz: KClass<R>): R = clazz.primaryConstructor!!.call(value)

/** Активность пользователя */
val Boolean.activity: Activity
    get() = Activity(this)

val Int.gender: Gender
    get() = Gender(this).getOrThrow()

val String.email: Email
    get() = Email(this).getOrThrow()
