package studio.juno.app.lyricsovh.domain.search_list.entity

import java.io.Serializable

/**
 * Representation of a song.
 * @param title title of the song.
 * @param artist artist name associated to song.
 */
data class Song (
    val id: Int,
    val title: String,
    val artist: Artist,
    val album: Album
) : Serializable