package studio.juno.app.lyricsovh.domain.search_list

import studio.juno.app.lyricsovh.domain.common.Either
import studio.juno.app.lyricsovh.domain.common.Failure
import studio.juno.app.lyricsovh.domain.search_list.entity.Song

/**
 *
 *
 */
interface SearchListRepository {

    /**
     *
     */
    suspend fun getSongsByText(text: String) : Either<Failure, List<Song>>

}