package studio.juno.app.lyricsovh.presentation.search_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import studio.juno.app.lyricsovh.R
import studio.juno.app.lyricsovh.domain.search_list.entity.Song

/**
 *
 *
 */
class SongsAdapter (
    private val onSongClickListener: (Song, ViewHolder) -> Unit
) : ListAdapter<Song, SongsAdapter.ViewHolder>(DIFF_CALLBACK) {

    /**
     *
     * @param itemView
     */
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivAlbumCover: ImageView = itemView.findViewById(R.id.ivAlbumCover)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }

    /**
     *
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.adapter_song, parent, false))
    }

    /**
     *
     *
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = getItem(position)
        holder.run {
            val context = itemView.context
            Glide.with(context).load(song.album.cover).into(ivAlbumCover)
            tvDescription.text =
                context.getString(R.string.song_artist_and_title, song.artist.name, song.title)
            itemView.setOnClickListener { onSongClickListener(song, this) }
        }
    }

    /**
     *
     *
     */
    companion object {

        /* */
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Song>() {

            /**
             *
             *
             */
            override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean =
                oldItem.id == newItem.id

            /**
             *
             *
             */
            override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean =
                oldItem == newItem

        }

    }

}
