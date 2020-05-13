package studio.juno.app.lyricsovh.data.search_list.remote

import retrofit2.http.GET
import retrofit2.http.Path
import studio.juno.app.lyricsovh.data.search_list.remote.SearchListApiService.URL.GET_SUGGEST
import studio.juno.app.lyricsovh.data.search_list.remote.model.GetSongsSuggestedByTextResponse

/**
 *
 *
 */
interface SearchListApiService {


    /**
     *
     *
     */
    private object URL {
        const val GET_SUGGEST: String = "/suggest/{search_term}"
    }

    /**
     *
     *
     */
    @GET(GET_SUGGEST)
    suspend fun getSongsSuggestedByText(
        @Path("search_term") text: String
    ): GetSongsSuggestedByTextResponse

}
