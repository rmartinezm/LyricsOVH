package studio.juno.app.lyricsovh.data.song_lyric

import studio.juno.app.lyricsovh.data.internet_connection.InternetConnectionRepository
import studio.juno.app.lyricsovh.domain.common.Either
import studio.juno.app.lyricsovh.domain.common.Failure
import studio.juno.app.lyricsovh.domain.song_lyric.SongLyricRepository

/**
 *
 * @param songLyricDataSource
 */
class SongLyricRepositoryImpl (
    private val songLyricDataSource: SongLyricDataSource,
    private val internetConnectionRepository: InternetConnectionRepository
) : SongLyricRepository {

    /* */
    private val isOnline: Boolean get() = internetConnectionRepository.isOnline

    /**
     *
     * @param artistName
     * @param songTitle
     */
    override suspend fun getLyricSong(
        artistName: String,
        songTitle: String
    ): Either<Failure, String> =
        if(isOnline)
            songLyricDataSource.getSongLyricFromArtistNameAndTitle(artistName, songTitle)
        else Either.Left(Failure.NetworkConnectionFailure)
}