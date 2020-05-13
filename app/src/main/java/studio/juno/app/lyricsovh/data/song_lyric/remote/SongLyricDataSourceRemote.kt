package studio.juno.app.lyricsovh.data.song_lyric.remote

import retrofit2.HttpException
import studio.juno.app.lyricsovh.data.common.HttpCode
import studio.juno.app.lyricsovh.data.song_lyric.SongLyricDataSource
import studio.juno.app.lyricsovh.domain.common.Either
import studio.juno.app.lyricsovh.domain.common.Failure
import studio.juno.app.lyricsovh.domain.song_lyric.failure.NotAvailableLyrics

/**
 *
 *
 */
class SongLyricDataSourceRemote (
    private val songLyricApiService: SongLyricApiService
) : SongLyricDataSource {

    /**
     *
     * @param artistName
     * @param songTitle
     */
    override suspend fun getSongLyricFromArtistNameAndTitle(
        artistName: String,
        songTitle: String
    ): Either<Failure, String> =
        try {
            val response = songLyricApiService.getLyricByArtistAndTitle(artistName, songTitle)
            Either.Right(response.lyrics)
        } catch (e: Exception){
            val code: Int? = when (e) {
                is HttpException -> e.code()
                else -> null
            }
            val failure = if(HttpCode.get(code) == HttpCode.NOT_FOUND)
                NotAvailableLyrics()
            else Failure.ServerFailure(e.message, code)
            Either.Left(failure)
        }

}