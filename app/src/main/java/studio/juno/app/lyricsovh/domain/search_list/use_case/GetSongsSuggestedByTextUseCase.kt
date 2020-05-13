package studio.juno.app.lyricsovh.domain.search_list.use_case

import studio.juno.app.lyricsovh.domain.common.Either
import studio.juno.app.lyricsovh.domain.common.Failure
import studio.juno.app.lyricsovh.domain.common.UseCase
import studio.juno.app.lyricsovh.domain.search_list.SearchListRepository
import studio.juno.app.lyricsovh.domain.search_list.entity.Song

/**
 *
 * @param searchListRepository
 */
class GetSongsSuggestedByTextUseCase (
    private val searchListRepository: SearchListRepository
) : UseCase<List<Song>, GetSongsSuggestedByTextUseCase.Params>() {

    /**
     * Parameters needed to call [GetSongsSuggestedByTextUseCase].
     */
    data class Params(val text: String)

    /**
     *
     * @param params
     * @return [Either]
     */
    override suspend fun run(params: Params): Either<Failure, List<Song>> =
        searchListRepository.getSongsByText(params.text)

}