package studio.juno.app.lyricsovh.presentation.search_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import studio.juno.app.lyricsovh.domain.common.Either
import studio.juno.app.lyricsovh.domain.common.Failure
import studio.juno.app.lyricsovh.domain.search_list.entity.Song
import studio.juno.app.lyricsovh.domain.search_list.use_case.GetSongsSuggestedByTextUseCase
import studio.juno.app.lyricsovh.presentation.common.normalize

/**
 *
 * @param getSongsSuggestedByTextUseCase
 */
class SearchListViewModel (
    private val getSongsSuggestedByTextUseCase: GetSongsSuggestedByTextUseCase
) : ViewModel() {

    /* */
    private var loadSongsSuggestedJob: Job? = null
    /* */
    private val _songsLiveData: MutableLiveData<List<Song>> = MutableLiveData()
    val songsLiveData: LiveData<List<Song>> get() = _songsLiveData
    /* */
    private val _failureLiveData: MutableLiveData<Failure> = MutableLiveData()
    val failureLiveData: LiveData<Failure> get() = _failureLiveData

    /**
     *
     *
     */
    fun loadSongsSuggested(text: String?) {
        cancelLoadSongsSuggested()
        if(text.isNullOrBlank())
            _songsLiveData.postValue(listOf())
        else
            CoroutineScope(Dispatchers.IO).launch { runGetSongsUseCase(text.trim()) }
    }

    /**
     *
     *
     */
    private fun runGetSongsUseCase(text: String) = runBlocking {
        val params = GetSongsSuggestedByTextUseCase.Params(text.normalize())
        loadSongsSuggestedJob = async { getSongsSuggestedByTextUseCase.run(params) }
        @Suppress("UNCHECKED_CAST")
        val response = (loadSongsSuggestedJob as Deferred<Either<Failure, List<Song>>>).await()
        response.fold(
            fnL = { _failureLiveData.postValue(it) },
            fnR = { _songsLiveData.postValue(it) }
        )
    }

    /**
     *
     *
     */
    private fun cancelLoadSongsSuggested() {
        loadSongsSuggestedJob?.cancel()
    }

}