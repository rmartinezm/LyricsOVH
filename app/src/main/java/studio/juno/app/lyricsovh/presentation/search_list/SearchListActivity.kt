package studio.juno.app.lyricsovh.presentation.search_list

import android.app.ActivityOptions
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import studio.juno.app.lyricsovh.R
import studio.juno.app.lyricsovh.domain.common.Failure
import studio.juno.app.lyricsovh.domain.search_list.entity.Song
import studio.juno.app.lyricsovh.presentation.common.empty
import studio.juno.app.lyricsovh.presentation.search_list.adapter.SongsAdapter
import studio.juno.app.lyricsovh.presentation.song_lyric.SongLyricActivity

/**
 *
 *
 */
class SearchListActivity : AppCompatActivity(),
    SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    /* */
    private val searchListViewModel: SearchListViewModel by viewModel()
    private lateinit var searchView: SearchView

    /**
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_list)
        initViews()
        observeViewModel()
    }

    /**
     *
     *
     */
    private fun initViews() {
        rvSongs?.adapter = SongsAdapter(::onSongClickListener)
        rvSongs?.layoutManager = LinearLayoutManager(this)
    }

    /**
     *
     *
     */
    private fun observeViewModel(){
        searchListViewModel.let {
            it.failureLiveData.observe(this, getFailureObserver())
            it.songsLiveData.observe(this, getSongsObserver())
        }
    }

    /**
     *
     *
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_list_menu, menu)
        configureSearchView(menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     *
     *
     */
    private fun configureSearchView(menu: Menu){
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        val searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        searchPlate.hint = getString(R.string.search_input_hint)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
    }

    /**
     *
     *
     */
    override fun onQueryTextSubmit(query: String?): Boolean = false

    /**
     *
     *
     */
    override fun onQueryTextChange(newText: String?): Boolean {
        onSearchInputTextChange(newText ?: String.empty)
        return false
    }

    /**
     *
     *
     */
    override fun onClose(): Boolean {
        layoutPlaceHolder.visibility = View.VISIBLE
        return false
    }

    /**
     *
     *
     */
    private fun onSearchInputTextChange(text: String?) {
        lavLoader.visibility = View.VISIBLE
        layoutPlaceHolder.visibility = View.GONE
        layoutSongNotFound.visibility = View.GONE
        noInternetConnectionLayout.visibility = View.GONE
        searchListViewModel.loadSongsSuggested(text)
    }

    /**
     *
     *
     */
    private fun getFailureObserver() = Observer<Failure> {
        lavLoader.visibility = View.GONE
        when(it){
            is Failure.NetworkConnectionFailure -> showNotInternetConnectionMessage()
            else -> Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
        (rvSongs.adapter as SongsAdapter).submitList(listOf())
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
    private fun getSongsObserver() = Observer<List<Song>> {
        lavLoader.visibility = View.GONE
        when {
            searchView.query.isNullOrEmpty() -> layoutPlaceHolder.visibility = View.VISIBLE
            it.isEmpty() -> layoutSongNotFound.visibility = View.VISIBLE
        }
        (rvSongs.adapter as SongsAdapter).submitList(it)
    }

    /**
     *
     *
     */
    private fun onSongClickListener(song: Song, holder: SongsAdapter.ViewHolder){
        val options = ActivityOptions.makeSceneTransitionAnimation(
            this,
            holder.ivAlbumCover,
            getString(R.string.iv_album_cover_transition_name)
        )
        Intent(this, SongLyricActivity::class.java).apply {
            putExtra(SongLyricActivity.SONG, song)
            startActivity(this, options.toBundle())
        }
    }

    /**
     *
     *
     */
    override fun onBackPressed() {
        if(searchView.isIconified)
            super.onBackPressed()
        else searchView.onActionViewCollapsed()
    }

}
