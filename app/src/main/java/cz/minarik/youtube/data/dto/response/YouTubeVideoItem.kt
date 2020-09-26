package cz.minarik.youtube.data.dto.response

import cz.minarik.youtube.ui.custom.YouTubeVideoListViewDTO

data class YouTubeVideoItem(
    val id: String? = null,
    val snippet: YouTubeSnippet? = null
) {
    fun toYoutubeVideo(): YouTubeVideoListViewDTO {
        return YouTubeVideoListViewDTO(
            id,
            snippet?.title,
            snippet?.description,
            snippet?.thumbnails?.maxres?.url
        )
    }
}