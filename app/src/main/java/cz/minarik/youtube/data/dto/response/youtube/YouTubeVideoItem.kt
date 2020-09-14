package cz.minarik.base.data.rest.dto.response.youtube

import cz.minarik.youtube.data.dto.model.YouTubeVideo

data class YouTubeVideoItem(
    val id: String? = null,
    val snippet: YouTubeSnippet? = null
) {
    fun toYoutubeVideo(): YouTubeVideo {
        return YouTubeVideo(
            id,
            snippet?.title,
            snippet?.description,
            snippet?.thumbnails?.maxres?.url
        )
    }
}