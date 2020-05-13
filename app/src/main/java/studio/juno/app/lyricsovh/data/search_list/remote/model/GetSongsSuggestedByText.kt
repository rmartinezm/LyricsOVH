package studio.juno.app.lyricsovh.data.search_list.remote.model

import studio.juno.app.lyricsovh.domain.search_list.entity.Album
import studio.juno.app.lyricsovh.domain.search_list.entity.Artist
import studio.juno.app.lyricsovh.domain.search_list.entity.Song

/**
 *
 *
 */
data class GetSongsSuggestedByTextResponse (
    val data: List<SongResponse>,
    val total: Int,
    val next: String
)

/**
 *
 *
 */
data class SongResponse (
    val id: Int,
    val title: String,
    val artist: ArtistResponse,
    val album: AlbumResponse
) {

    /**
     *
     *
     */
    fun toSongEntity() = Song (
        id,
        title,
        artist.toArtistEntity(),
        album.toAlbumEntity()
    )

}

data class ArtistResponse (
    val name: String,
    val picture_medium: String?
) {

    /**
     *
     *
     */
    fun toArtistEntity() = Artist (name, picture_medium)

}

/**
 *
 *
 */
data class AlbumResponse (
    val title: String,
    val cover_medium: String?
) {

    /**
     *
     *
     */
    fun toAlbumEntity() = Album (title, cover_medium)

}
