package studio.juno.app.lyricsovh.data.search_list

import studio.juno.app.lyricsovh.domain.common.Either
import studio.juno.app.lyricsovh.domain.common.Failure
import studio.juno.app.lyricsovh.domain.search_list.entity.Song

/**
 *
 *
 */
interface SearchListDataSource {

    /**
     *
     *
     */
    suspend fun getSongsSuggestedByText(text: String) : Either<Failure, List<Song>>

}
