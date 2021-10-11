package com.org.core.home.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeezerAlbumResponse(
    @SerialName("data")
    val data: List<AlbumData>
)

@Serializable
data class AlbumData(
    @SerialName("id")
    val id: Long?,
    @SerialName("title")
    val title: String?,
    @SerialName("link")
    val link: String?,
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
    @SerialName("nb_tracks")
    val nbTracks: Int?,
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
    @SerialName("time_add")
    val timeAdd: Long?,
    @SerialName("artist")
    val artist: Artist?,
    @SerialName("type")
    val type: String?

) {
    @Serializable
    data class Artist(
        @SerialName("id")
        val id: Long?,
        @SerialName("name")
        val name: String?,
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
        @SerialName("tracklist")
        val trackList: String?,
        @SerialName("type")
        val type: String?
    )
}

@Serializable
data class TrackResponse(
    @SerialName("data")
    val data: List<TracksData>?
)

@Serializable
data class TracksData(
    @SerialName("id")
    val id: Long?,
    @SerialName("readable")
    val readable: Boolean?,
    @SerialName("title")
    val title: String,
    @SerialName("title_short")
    val titleShort: String,
    @SerialName("isrc")
    val isrc: String,
    @SerialName("link")
    val link: String,
    @SerialName("duration")
    val duration: Long,
    @SerialName("track_position")
    val trackPosition: Int?,
    @SerialName("disk_number")
    val diskNumber: Int?,
    @SerialName("rank")
    val rank: Int?,
    @SerialName("explicit_lyrics")
    val explicityLyrics: Boolean?,
    @SerialName("explicit_content_lyrics")
    val explicityContentLyrics: Int?,
    @SerialName("explicit_content_cover")
    val explicityContentCover: Int?,
    @SerialName("preview")
    val preview: String?,
    @SerialName("md5_image")
    val md5Image: String?,
    @SerialName("artist")
    val artist: Artist?,
    @SerialName("type")
    val type: String?

) {
    @Serializable
    data class Artist(
        @SerialName("id")
        val id: Long?,
        @SerialName("name")
        val name: String,
        @SerialName("tracklist")
        val trackList: String,
        @SerialName("type")
        val type: String
    )
}