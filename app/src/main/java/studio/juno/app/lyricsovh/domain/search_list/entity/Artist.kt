package studio.juno.app.lyricsovh.domain.search_list.entity

import java.io.Serializable

/**
 * Representation of an Artist.
 * @param name artist name.
 * @param picture url image associated to the artist.
 */
data class Artist (
    val name: String,
    val picture: String?
) : Serializable