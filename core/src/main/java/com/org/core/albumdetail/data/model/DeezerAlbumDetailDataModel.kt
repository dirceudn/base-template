package com.org.core.albumdetail.data.model

import com.org.core.albumdetail.api.model.DeezerAlbumDetailResponse
import com.org.core.home.api.model.TrackResponse
import com.org.core.home.api.model.TracksData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDetailErrorNetworkModel(
    @SerialName("error")
    val error: Error,
)

@Serializable
data class Error(
    @SerialName("type")
    val type: String?,
    @SerialName("message")
    val message: String?,
    @SerialName("code")
    val code: Int
)


data class DeezerAlbumTracksDataModel(
    val data: List<TracksDataModel>?
)

data class TracksDataModel(
    val id: Long?,
    val title: String?,
    val readable: Boolean?,
    val titleShort: String,
    val duration: Long?,
    val trackPosition: Int?,
    val diskNumber: Int?,
    val picture: String? = null,
    val preview: String?
)

fun TracksData.toTrackDataModel() = TracksDataModel(
    id = id,
    title = title,
    titleShort = titleShort,
    readable = readable,
    duration = duration,
    trackPosition = trackPosition,
    diskNumber = diskNumber,
    preview = preview
)

fun TrackResponse.toDeezerAlbumTrackDataModel() = DeezerAlbumTracksDataModel(
    data = this.data?.map { trackResponse -> trackResponse.toTrackDataModel() } ?: listOf()
)

data class DeezerAlbumDetailDataModel(
    val id: Long?,
    val title: String?,
    val cover: String?,
    val coverSmall: String?,
    val coverMedium: String?,
    val coverBig: String?,
    val coverXl: String?,
    val genreId: Int?,
    val label: String?,
    val releaseDate: String?,
    val genres: List<GenreDataModel>?,
    val contributors: List<ContributorDataModel>?
)

data class GenreDataModel(
    val id: Long?,
    val name: String?,
    val picture: String?,
    val type: String?
)

data class ContributorDataModel(
    val id: Long?,
    val name: String?,
    val picture: String?,
    val pictureSmall: String?,
    val pictureMedium: String?,
    val pictureBig: String?,
    val pictureXl: String?,
    val role: String?
)

fun DeezerAlbumDetailResponse.Contributor.toContributorDataModel() = ContributorDataModel(
    id = id,
    name = name,
    picture = picture,
    pictureSmall = pictureSmall,
    pictureMedium = pictureMedium,
    pictureBig = pictureBig,
    pictureXl = pictureXl,
    role = role
)

fun DeezerAlbumDetailResponse.toDataModel() = DeezerAlbumDetailDataModel(
    id = id,
    title = title,
    cover = cover,
    coverSmall = coverSmall,
    coverMedium = coverMedium,
    coverBig = coverBig,
    coverXl = coverXl,
    genreId = genreId,
    label = label,
    releaseDate = releaseDate,
    genres = genre.data?.map { genreData -> genreData.toGenreDataModel() } ?: listOf(),
    contributors = contributors?.map { contributor -> contributor.toContributorDataModel() }
        ?: listOf()
)

fun DeezerAlbumDetailResponse.Genre.GenreData.toGenreDataModel() = GenreDataModel(
    id = id,
    name = name,
    picture = picture,
    type = type
)