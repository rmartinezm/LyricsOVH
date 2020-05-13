package studio.juno.app.lyricsovh.di

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import studio.juno.app.lyricsovh.data.song_lyric.SongLyricDataSource
import studio.juno.app.lyricsovh.data.song_lyric.SongLyricRepositoryImpl
import studio.juno.app.lyricsovh.data.song_lyric.remote.SongLyricApiService
import studio.juno.app.lyricsovh.data.song_lyric.remote.SongLyricDataSourceRemote
import studio.juno.app.lyricsovh.domain.search_list.entity.Song
import studio.juno.app.lyricsovh.domain.song_lyric.SongLyricRepository
import studio.juno.app.lyricsovh.domain.song_lyric.use_case.GetSongLyricByArtistAndTitleUseCase
import studio.juno.app.lyricsovh.presentation.song_lyric.SongLyricViewModel

/**
 *
 *
 */
val songLyricModule = module {

    /* Presentation */
    viewModel { (song: Song) -> SongLyricViewModel(song, getSongLyricByArtistAndTitleUseCase = get()) }

    /* Domain */
    factory { GetSongLyricByArtistAndTitleUseCase(songLyricRepository = get()) }
    single<SongLyricRepository> {
        SongLyricRepositoryImpl(
            songLyricDataSource = get(),
            internetConnectionRepository = get()
        )
    }

    /* Data */
    single<SongLyricDataSource> { SongLyricDataSourceRemote(songLyricApiService = get()) }
    single<SongLyricApiService> { get<Retrofit>().create(SongLyricApiService::class.java) }

}