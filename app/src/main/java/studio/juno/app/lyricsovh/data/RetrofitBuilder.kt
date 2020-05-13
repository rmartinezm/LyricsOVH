package studio.juno.app.lyricsovh.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * @param baseUrl
 */
class RetrofitBuilder(private val baseUrl: String) {

    /* */
    private val bodyInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    /* */
    private val gsonConverterFactory = GsonConverterFactory.create()
    /* */
    private val coroutineCallAdapterFactory = CoroutineCallAdapterFactory()
    /* */
    private val timeOut: Long = 100L

    /**
     *
     * @return
     */
    fun build() : Retrofit =
        Retrofit.Builder()
            .client(buildHttpClient())
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .build()

    /**
     *
     * @return [OkHttpClient]
     */
    private fun buildHttpClient() =
        OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .apply { addInterceptor(bodyInterceptor) }
            .build()

}