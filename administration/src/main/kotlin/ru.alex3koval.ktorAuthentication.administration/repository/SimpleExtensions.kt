package ru.alex3koval.ktorAuthentication.administration.repository

import org.jetbrains.exposed.dao.id.EntityID
import ru.alex3koval.ktorAuthentication.administration.vo.Activity
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

/**
 * VO ID из PK таблицы
 */
fun<T: EntityID<*>, R : Any> T.id(clazz: KClass<R>): R = clazz.primaryConstructor!!.call(value)

/** Активность пользователя */
inline val Boolean.activity: Activity
    get() = Activity(this)
