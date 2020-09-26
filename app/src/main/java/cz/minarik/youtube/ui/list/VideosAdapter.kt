package cz.minarik.youtube.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cz.minarik.youtube.R
import cz.minarik.youtube.ui.custom.YouTubeVideoListViewDTO
import cz.minarik.youtube.ui.custom.YoutubeVideoListItem

class VideosAdapter(private var onItemClicked: (video: YouTubeVideoListViewDTO, imageView: ImageView) -> Unit) :
    PagedListAdapter<YouTubeVideoListViewDTO, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<YouTubeVideoListViewDTO>() {

            override fun areItemsTheSame(oldItem: YouTubeVideoListViewDTO, newItem: YouTubeVideoListViewDTO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: YouTubeVideoListViewDTO, newItem: YouTubeVideoListViewDTO): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_youtube_video, parent, false)
        )
    }

    class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val youtubeVideoItem: YoutubeVideoListItem = view.findViewById(R.id.youtubeVideoItem)

        fun bind(
            video: YouTubeVideoListViewDTO?,
            onItemClicked: (video: YouTubeVideoListViewDTO, imageView: ImageView) -> Unit
        ) {
            if (video == null) return
            youtubeVideoItem.set(video)
            youtubeVideoItem.setOnClickListener {
                onItemClicked(video, youtubeVideoItem.thumbnailImageView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as VideoViewHolder).bind(getItem(position), onItemClicked)
    }
}