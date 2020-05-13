package studio.juno.app.lyricsovh.data.song_lyric.remote

import retrofit2.http.GET
import retrofit2.http.Path
import studio.juno.app.lyricsovh.data.song_lyric.remote.SongLyricApiService.URL.GET_LYRIC_BY_ARTIST_AND_TITLE
import studio.juno.app.lyricsovh.data.song_lyric.remote.model.GetLyricByArtistAndTitleResponse

/**
 *
 *
 */
interface SongLyricApiService {

    /**
     *
     *
     */
    private object URL {
        const val GET_LYRIC_BY_ARTIST_AND_TITLE: String = "/v1/{artist}/{song}"
    }

    /**
     *
     *
     */
    @GET(GET_LYRIC_BY_ARTIST_AND_TITLE)
    suspend fun getLyricByArtistAndTitle(
        @Path("artist") artistName: String,
        @Path("song") songTitle: String
    ) : GetLyricByArtistAndTitleResponse
}