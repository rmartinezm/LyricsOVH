package studio.juno.app.lyricsovh.data.search_list.remote

import retrofit2.HttpException
import studio.juno.app.lyricsovh.data.search_list.SearchListDataSource
import studio.juno.app.lyricsovh.domain.common.Either
import studio.juno.app.lyricsovh.domain.common.Failure
import studio.juno.app.lyricsovh.domain.search_list.entity.Song

/**
 *
 *
 */
class SearchListDataSourceRemote (
    private val searchListApiService: SearchListApiService
) : SearchListDataSource {

    /**
     *
     * @param text
     * @return [Either]
     */
    override suspend fun getSongsSuggestedByText(text: String): Either<Failure, List<Song>> =
        try {
            val response = searchListApiService.getSongsSuggestedByText(text)
            val songs = response.data.map { it.toSongEntity() }
            Either.Right(songs)
        } catch (e: Exception){
            val code: Int? = when (e) {
                is HttpException -> e.code()
                else -> null
            }
            Either.Left(Failure.ServerFailure(e.message, code))
        }


}