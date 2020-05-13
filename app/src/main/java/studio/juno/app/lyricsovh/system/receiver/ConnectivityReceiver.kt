package studio.juno.app.lyricsovh.system.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import studio.juno.app.lyricsovh.data.internet_connection.InternetConnectionRepository

/**
 *
 *
 */
class ConnectivityReceiver : BroadcastReceiver(), KoinComponent {

    /* */
    private val internetConnectionRepository: InternetConnectionRepository by inject()

    /**
     *
     * @param context
     * @param intent
     */
    override fun onReceive(context: Context?, intent: Intent?) = runBlocking {
        withContext(Dispatchers.IO){
            internetConnectionRepository.fetch()
        }
    }

    /**
     *
     *
     */
    companion object {
        /* */
        const val connectivityChangeInputFilter: String = "android.net.conn.CONNECTIVITY_CHANGE"
    }

}