package cz.minarik.youtube.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import coil.api.load
import cz.minarik.youtube.R
import java.io.Serializable


class YoutubeVideoListItem(context: Context, attrs: AttributeSet? = null) :
    LinearLayout(context, attrs) {

    private val videoBackGround: ViewGroup
    val thumbnailImageView: ImageView
    private val titleTextView: TextView

    init {
        inflate(context, R.layout.youtube_video_item, this)

        videoBackGround = findViewById(R.id.videoBackGround)
        thumbnailImageView = findViewById(R.id.thumbnailImageView)
        titleTextView = findViewById(R.id.titleTextView)
    }

    fun set(video: YouTubeVideoListViewDTO) {
        titleTextView.text = video.title

        thumbnailImageView.load(video.maxResThumbnailUrl) {
            crossfade(true)
        }

        thumbnailImageView.transitionName = video.maxResThumbnailUrl
        invalidate()
        requestLayout()
    }

    override fun setOnClickListener(l: OnClickListener?) {
        videoBackGround.setOnClickListener(l)
    }
}


data class YouTubeVideoListViewDTO(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val maxResThumbnailUrl: String? = null
) : Serializable