package studio.juno.app.lyricsovh

import android.app.Application
import android.content.IntentFilter
import org.koin.android.ext.android.startKoin
import studio.juno.app.lyricsovh.di.internetConnectionManagerModule
import studio.juno.app.lyricsovh.di.networkModule
import studio.juno.app.lyricsovh.di.searchListModule
import studio.juno.app.lyricsovh.di.songLyricModule
import studio.juno.app.lyricsovh.system.receiver.ConnectivityReceiver

/**
 *
 *
 */
class LyricsOvhApplication : Application() {

    /**
     *
     *
     */
    override fun onCreate() {
        super.onCreate()
        initKoin()
        initBroadcastReceiver()
    }


    /**
     *
     *
     */
    private fun initBroadcastReceiver(){
        registerReceiver(
            ConnectivityReceiver(),
            IntentFilter(ConnectivityReceiver.connectivityChangeInputFilter)
        )
    }
    /**
     *
     *
     */
    private fun initKoin(){
        val modules = listOf(
            internetConnectionManagerModule,
            networkModule,
            searchListModule,
            songLyricModule
        )
        startKoin(this, modules)
    }

}