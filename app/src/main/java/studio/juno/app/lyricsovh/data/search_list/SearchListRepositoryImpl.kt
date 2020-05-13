package studio.juno.app.lyricsovh.data.search_list

import studio.juno.app.lyricsovh.data.internet_connection.InternetConnectionRepository
import studio.juno.app.lyricsovh.domain.common.Either
import studio.juno.app.lyricsovh.domain.common.Failure
import studio.juno.app.lyricsovh.domain.search_list.SearchListRepository
import studio.juno.app.lyricsovh.domain.search_list.entity.Song

/**
 *
 * @param searchListDataSource
 */
class SearchListRepositoryImpl (
    private val searchListDataSource: SearchListDataSource,
    private val internetConnectionRepository: InternetConnectionRepository
) : SearchListRepository {

    /* */
    private val isOnline: Boolean get() = internetConnectionRepository.isOnline

    /**
     *
     *
     */
    override suspend fun getSongsByText(text: String): Either<Failure, List<Song>> =
        if(isOnline)
            searchListDataSource.getSongsSuggestedByText(text)
        else Either.Left(Failure.NetworkConnectionFailure)

}