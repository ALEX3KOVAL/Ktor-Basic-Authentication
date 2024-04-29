package ru.alex3koval.ktorAuthentication.configure

import org.jetbrains.exposed.sql.Database
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.core.logger.PrintLogger
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.alex3koval.ktorAuthentication.administration.repository.ClientRepository
import javax.sql.DataSource

/**
 * DI-Контейнер
 *
 * @param additionalModules Модули, переданные некоторым приложением для инициализации в контейнере
 */
class DI(additionalModules: List<Module> = listOf()) {
    val app: Koin by lazy {
        startKoin {
            logger(PrintLogger())

            modules(baseModules)
            modules(additionalModules)
        }.koin
    }

    /** Модуль репозиториев */
    private val reposModule: Module = module {
        factoryOf(::ClientRepository)
    }

    /** Модуль БД */
    private val dbModule: Module = module {
        single<Database>(createdAtStart = true) {
            logger.info("#####   Connect to PGSQL")
            Database.connect(get<DataSource>(named("pgsql")))
        }
    }

    /** Модули? которые будут инициализированы в контейнере вне зависимости от точки входа */
    private val baseModules: List<Module> = listOf(
        module {
            single<Settings> { Settings.read() }
        },
        reposModule,
        dbModule
    )
}