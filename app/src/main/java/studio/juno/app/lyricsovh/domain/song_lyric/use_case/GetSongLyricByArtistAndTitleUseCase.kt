package studio.juno.app.lyricsovh.domain.song_lyric.use_case

import studio.juno.app.lyricsovh.domain.common.Either
import studio.juno.app.lyricsovh.domain.common.Failure
import studio.juno.app.lyricsovh.domain.common.UseCase
import studio.juno.app.lyricsovh.domain.song_lyric.SongLyricRepository

/**
 *
 * @param songLyricRepository
 */
class GetSongLyricByArtistAndTitleUseCase (
    private val songLyricRepository: SongLyricRepository
) : UseCase<String, GetSongLyricByArtistAndTitleUseCase.Params>() {

    /**
     *
     * @param artistName
     * @param songTitle
     */
    data class Params (
        val artistName: String,
        val songTitle: String
    )

    /**
     *
     * @param params
     * @return [Either]
     */
    override suspend fun run(params: Params): Either<Failure, String> =
        songLyricRepository.getLyricSong(params.artistName, params.songTitle)

}