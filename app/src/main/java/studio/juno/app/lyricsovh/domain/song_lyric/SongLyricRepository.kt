package studio.juno.app.lyricsovh.domain.song_lyric

import studio.juno.app.lyricsovh.domain.common.Either
import studio.juno.app.lyricsovh.domain.common.Failure

/**
 *
 *
 */
interface SongLyricRepository {

    /**
     *
     * @param artistName
     * @param songTitle
     */
    suspend fun getLyricSong(artistName: String, songTitle: String) : Either<Failure, String>

}