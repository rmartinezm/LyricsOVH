package studio.juno.app.lyricsovh.di

import org.koin.dsl.module.module
import retrofit2.Retrofit
import studio.juno.app.lyricsovh.BuildConfig
import studio.juno.app.lyricsovh.data.RetrofitBuilder

/**
 * Creates an module that contains the single Retrofit instance to use with data source remote
 * implementation.
 */
val networkModule = module {
    single<Retrofit> { RetrofitBuilder(BuildConfig.API_LYRICS_URL).build() }
}