package studio.juno.app.lyricsovh.data.internet_connection

/**
 *
 *
 */
interface InternetConnectionRepository {

    val isOnline: Boolean

    /**
     *
     *
     */
    suspend fun fetch()

}
