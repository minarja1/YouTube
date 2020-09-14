package cz.minarik.youtube.data.dto.model

import java.io.Serializable

data class YouTubeVideo(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val maxResThumbnailUrl: String? = null
) : Serializable