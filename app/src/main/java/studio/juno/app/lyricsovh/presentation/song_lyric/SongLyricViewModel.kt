package studio.juno.app.lyricsovh.presentation.song_lyric

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import studio.juno.app.lyricsovh.domain.common.Failure
import studio.juno.app.lyricsovh.domain.search_list.entity.Song
import studio.juno.app.lyricsovh.domain.song_lyric.use_case.GetSongLyricByArtistAndTitleUseCase

/**
 *
 * @param song
 */
class SongLyricViewModel (
    private val song: Song,
    private val getSongLyricByArtistAndTitleUseCase: GetSongLyricByArtistAndTitleUseCase
) : ViewModel() {

    /* */
    private val _lyricLiveData: MutableLiveData<String> = MutableLiveData()
    val lyricLiveData: LiveData<String> get() = _lyricLiveData
    /* */
    private val _failureLiveData: MutableLiveData<Failure> = MutableLiveData()
    val failureLiveData: LiveData<Failure> get() = _failureLiveData

    /**
     *
     *
     */
    fun loadSongLyric() = viewModelScope.launch {
        val params = GetSongLyricByArtistAndTitleUseCase.Params(song.artist.name, song.title)
        withContext(Dispatchers.IO) {
            getSongLyricByArtistAndTitleUseCase(params){
                it.fold(::onGetSongLyricByArtistAndTitleFailure, ::onGetSongLyricByArtistAndTitleRight)
            }
        }
    }

    /**
     *
     *
     */
    private fun onGetSongLyricByArtistAndTitleFailure(failure: Failure){
        _failureLiveData.postValue(failure)
    }

    /**
     *
     *
     */
    private fun onGetSongLyricByArtistAndTitleRight(lyric: String){
        _lyricLiveData.postValue(lyric)

    }

}