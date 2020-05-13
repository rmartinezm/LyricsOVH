package studio.juno.app.lyricsovh.domain.search_list.entity

import java.io.Serializable

/**
 * Representation of an Album.
 * @param title album name.
 * @param cover url image associated to the album.
 */
data class Album (
    val title: String,
    val cover: String?
) : Serializable