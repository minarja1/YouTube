package cz.minarik.youtube.data.dto.response

import cz.minarik.youtube.ui.custom.YouTubeVideoListViewDTO

data class YouTubeVideosResponse(
    val kind: String? = null,
    val etag: String? = null,
    val nextPageToken: String? = null,
    val prevPageToken: String? = null,
    val pageInfo: YouTubePageInfo? = null,
    val items: List<YouTubeVideoItem?>? = null
) {
    fun toVideoList(): MutableList<YouTubeVideoListViewDTO> {
        val result = mutableListOf<YouTubeVideoListViewDTO>()
        if (items.isNullOrEmpty()) return result
        for (item in items) {
            item?.let {
                result.add(it.toYoutubeVideo())
            }
        }
        return result
    }
}