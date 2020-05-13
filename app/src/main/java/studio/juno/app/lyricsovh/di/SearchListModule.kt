package studio.juno.app.lyricsovh.di

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import studio.juno.app.lyricsovh.data.search_list.SearchListDataSource
import studio.juno.app.lyricsovh.data.search_list.SearchListRepositoryImpl
import studio.juno.app.lyricsovh.data.search_list.remote.SearchListApiService
import studio.juno.app.lyricsovh.data.search_list.remote.SearchListDataSourceRemote
import studio.juno.app.lyricsovh.domain.search_list.SearchListRepository
import studio.juno.app.lyricsovh.domain.search_list.use_case.GetSongsSuggestedByTextUseCase
import studio.juno.app.lyricsovh.presentation.search_list.SearchListViewModel

/**
 *
 *
 */
val searchListModule = module {

    /* Presentation */
    viewModel { SearchListViewModel(getSongsSuggestedByTextUseCase = get()) }

    /* Domain */
    factory { GetSongsSuggestedByTextUseCase(searchListRepository = get()) }
    single<SearchListRepository> {
        SearchListRepositoryImpl(
            searchListDataSource = get(),
            internetConnectionRepository = get()
        )
    }

    /* Data */
    single<SearchListDataSource> { SearchListDataSourceRemote(searchListApiService = get()) }
    single<SearchListApiService> { get<Retrofit>().create(SearchListApiService::class.java) }

}