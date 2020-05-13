package studio.juno.app.lyricsovh.presentation.song_lyric

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_song_lyric.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import studio.juno.app.lyricsovh.R
import studio.juno.app.lyricsovh.domain.common.Failure
import studio.juno.app.lyricsovh.domain.search_list.entity.Song
import studio.juno.app.lyricsovh.domain.song_lyric.failure.NotAvailableLyrics

/**
 *
 *
 */
class SongLyricActivity : AppCompatActivity() {

    /* */
    companion object { const val SONG: String = "SONG" }
    /* */
    private lateinit var song: Song
    /* */
    private val songLyricViewModel: SongLyricViewModel by viewModel { parametersOf(song) }

    /**
     *
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_lyric)
        initValues()
        initViews()
        observeViewModel()
        execute()
    }

    /**
     *
     *
     */
    private fun initValues(){
        song = intent.getSerializableExtra(SONG) as Song
    }

    /**
     *
     *
     */
    private fun initViews(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        song.let {
            Glide.with(this).load(song.album.cover).into(ivAlbumCover)
            tvSongName.text = it.title
            tvAlbumName.text = it.album.title
            tvArtistName.text = it.artist.name
        }
    }

    /**
     *
     *
     */
    private fun observeViewModel(){
        songLyricViewModel.let {
            it.lyricLiveData.observe(this, getSongLyricObserver())
            it.failureLiveData.observe(this, getFailureObserver())
        }
    }

    /**
     *
     *
     */
    private fun execute(){
        lavLoader.visibility = View.VISIBLE
        songLyricViewModel.loadSongLyric()
    }

    /**
     *
     *
     */
    private fun getSongLyricObserver() = Observer<String> {
        lavLoader.visibility = View.GONE
        tvSongLyric?.text = it
    }

    /**
     *
     *
     */
    private fun getFailureObserver() = Observer<Failure> {
        lavLoader.visibility = View.GONE
        when(it){
            is NotAvailableLyrics -> showNotAvailableLyricsViews()
            is Failure.NetworkConnectionFailure -> showNotInternetConnectionMessage()
            else -> Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    /**
     *
     *
     */
    private fun showNotAvailableLyricsViews(){
        tvNotAvailableLyrics.visibility = View.VISIBLE
    }

    /**
     *
     *
     */
    private fun showNotInternetConnectionMessage(){
        noInternetConnectionLayout.visibility = View.VISIBLE
    }

    /**
     *
     *
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
