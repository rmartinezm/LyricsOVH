package studio.juno.app.lyricsovh.data.song_lyric

import studio.juno.app.lyricsovh.domain.common.Either
import studio.juno.app.lyricsovh.domain.common.Failure

/**
 *
 *
 */
interface SongLyricDataSource {

    /**
     *
     * @param artistName
     * @param songTitle
     */
    suspend fun getSongLyricFromArtistNameAndTitle(
        artistName: String,
        songTitle: String
    ) : Either<Failure, String>

}