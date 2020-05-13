package studio.juno.app.lyricsovh.data.internet_connection

import android.os.AsyncTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

/**
 *
 *
 */
class InternetConnectionRepositoryImpl : InternetConnectionRepository {

    /* */
    private var _isOnline: Boolean = true
    /* */
    override val isOnline: Boolean get() = _isOnline

    /**
     *
     *
     */
    override suspend fun fetch() {
        CheckInternetTask { _isOnline = it }.execute()
    }

    /**
     *
     * @param callback
     */
    internal class CheckInternetTask(
        val callback: (value: Boolean) -> Unit
    ) : AsyncTask<Void, Void, Boolean>() {

        /* */
        private val calledUrl = "https://clients3.google.com/generate_204"
        /* */
        private val connectTimeout = 1500


        /**
         *
         * @param params
         */
        override fun doInBackground(vararg params: Void): Boolean? = try {
            val url = URL(calledUrl)
            (url.openConnection() as HttpURLConnection).run {
                setRequestProperty("User-Agent", "Android")
                setRequestProperty("Connection", "close")
                connectTimeout = this@CheckInternetTask.connectTimeout
                connect()
                responseCode == 204 && contentLength == 0
            }
        } catch (ignore: Exception) { false }

        /**
         *
         * @param isInternetAvailable
         */
        override fun onPostExecute(isInternetAvailable: Boolean?) {
            callback(isInternetAvailable ?: false)
        }

    }

}