package ru.alex3koval.ktorAuthentication.server.factory

import com.zaxxer.hikari.HikariDataSource
import org.koin.core.Koin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.alex3koval.ktorAuthentication.configure.DI
import ru.alex3koval.ktorAuthentication.configure.Settings
import ru.alex3koval.ktorAuthentication.server.security.SecurityService
import javax.sql.DataSource

/**
 * Внедрение зависимостей
 */
object DIBuilder {
    val di: Koin by lazy {
        DI(
            listOf(
                module {
                    single<DataSource>(named("pgsql")) {
                        val env: Settings = get()

                        HikariDataSource().also {
                            it.jdbcUrl = env.mainDB.jdbcURL
                            it.username = env.mainDB.login
                            it.password = env.mainDB.password
                            it.maximumPoolSize = env.mainDB.maximumPoolSize
                        }
                    }
                },
                module {
                    single<SecurityService> {
                        val env: Settings = get()

                        SecurityService(env.securitySecretKey.value)
                    }
                }
            )
        ).app
    }
}