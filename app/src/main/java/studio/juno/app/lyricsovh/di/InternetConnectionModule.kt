package studio.juno.app.lyricsovh.di

import org.koin.dsl.module.module
import studio.juno.app.lyricsovh.data.internet_connection.InternetConnectionRepository
import studio.juno.app.lyricsovh.data.internet_connection.InternetConnectionRepositoryImpl

/**
 *
 *
 */
val internetConnectionManagerModule = module {

    /* Repository */
    single<InternetConnectionRepository>(createOnStart = true) {
        InternetConnectionRepositoryImpl()
    }

}