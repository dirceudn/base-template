package com.org.core.albumdetail.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeezerAlbumDetailResponse(
    @SerialName("id")
    val id: Long?,
    @SerialName("title")
    val title: String?,
    @SerialName("upc")
    val upc: String?,
    @SerialName("link")
    val link: String?,
    @SerialName("share")
    val share: String?,
    @SerialName("cover")
    val cover: String?,
    @SerialName("cover_small")
    val coverSmall: String?,
    @SerialName("cover_medium")
    val coverMedium: String?,
    @SerialName("cover_big")
    val coverBig: String?,
    @SerialName("cover_xl")
    val coverXl: String?,
    @SerialName("md5_image")
    val md5Image: String?,
    @SerialName("genre_id")
    val genreId: Int?,
    @SerialName("genres")
    val genre: Genre,
    @SerialName("label")
    val label: String,
    @SerialName("nb_tracks")
    val nbTracks: Int?,
    @SerialName("duration")
    val duration: Long?,
    @SerialName("fans")
    val fans: Long?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("record_type")
    val recordType: String?,
    @SerialName("available")
    val available: Boolean?,
    @SerialName("tracklist")
    val trackList: String?,
    @SerialName("explicit_lyrics")
    val explicityLyrics: Boolean?,
    @SerialName("explicit_content_lyrics")
    val explicityContentLyrics: Int?,
    @SerialName("explicit_content_cover")
    val explicityContentCover: Int?,
    @SerialName("contributors")
    val contributors: List<Contributor>?
) {
    @Serializable
    data class Genre(
        @SerialName("data")
        val data: List<GenreData>?
    ) {
        @Serializable
        data class GenreData(
            @SerialName("id")
            val id: Long?,
            @SerialName("name")
            val name: String?,
            @SerialName("picture")
            val picture: String?,
            @SerialName("type")
            val type: String?
        )
    }

    @Serializable
    data class Contributor(
        @SerialName("id")
        val id: Long?,
        @SerialName("name")
        val name: String?,
        @SerialName("link")
        val link: String?,
        @SerialName("share")
        val share: String?,
        @SerialName("picture")
        val picture: String?,
        @SerialName("picture_small")
        val pictureSmall: String?,
        @SerialName("picture_medium")
        val pictureMedium: String?,
        @SerialName("picture_big")
        val pictureBig: String?,
        @SerialName("picture_xl")
        val pictureXl: String?,
        @SerialName("radio")
        val radio: Boolean?,
        @SerialName("tracklist")
        val trackList: String?,
        @SerialName("type")
        val type: String?,
        @SerialName("role")
        val role: String?
    )
}