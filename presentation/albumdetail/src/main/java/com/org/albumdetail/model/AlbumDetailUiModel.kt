package com.org.albumdetail.model

import com.org.core.albumdetail.data.model.*
import com.org.core.common.formatDate
import com.org.core.common.formatDuration


sealed class TrackAlbumUiState {
    data class ListTracksUiModel(
        val tracks: List<TracksUiModel>?
    ) : TrackAlbumUiState()

    data class TracksUiModel(
        val id: Long?,
        val title: String?,
        val readable: Boolean?,
        val titleShort: String,
        val duration: String?,
        val trackPosition: Int?,
        val diskNumber: Int?,
        val picture: String? = null,
        val preview: String?
    ) : TrackAlbumUiState()

    object NoTracksFound : TrackAlbumUiState()
    object TracksEmpty : TrackAlbumUiState()
    object TracksLoading : TrackAlbumUiState()

}

sealed class AlbumDetailUiState {
    data class AlbumDetailUiModel(
        val id: Long? = 0L,
        val title: String?,
        val cover: String?,
        val coverSmall: String?,
        val coverBig: String?,
        val coverXl: String?,
        val label: String?,
        val releaseDate: String?,
        val genresUiModels: List<GenreUiModel>?,
        val contributorsUiModel: List<ContributorUiModel>?
    ) : AlbumDetailUiState()

    object AlbumNotFound : AlbumDetailUiState()
    object AlbumLoading : AlbumDetailUiState()
    object AlbumEmpty : AlbumDetailUiState()
}


data class ContributorUiModel(
    val id: Long?,
    val name: String?,
    val picture: String?,
    val pictureSmall: String?,
    val pictureMedium: String?,
    val pictureBig: String?,
    val pictureXl: String?,
    val role: String?
)

fun DeezerAlbumTracksDataModel.toTrackAlbumDataUiModel() = TrackAlbumUiState.ListTracksUiModel(
    tracks = this.data?.map { tracksDataModel -> tracksDataModel.toTrackUiModel() }
        ?: listOf()
)

fun TracksDataModel.toTrackUiModel() = TrackAlbumUiState.TracksUiModel(
    id = id,
    title = title,
    titleShort = titleShort,
    readable = readable,
    duration = duration?.formatDuration(),
    trackPosition = trackPosition,
    diskNumber = diskNumber,
    preview = preview
)

fun ContributorDataModel.toContributorUiModel() = ContributorUiModel(
    id = id,
    name = name,
    picture = picture,
    pictureSmall = pictureSmall,
    pictureMedium = pictureMedium,
    pictureBig = pictureBig,
    pictureXl = pictureXl,
    role = role
)

data class GenreUiModel(val id: Long?, val name: String?, val picture: String?)

fun GenreDataModel.toGenreUiModel() = GenreUiModel(id = id, name = name, picture = picture)

fun DeezerAlbumDetailDataModel.toAlbumDetailUiModel() = AlbumDetailUiState.AlbumDetailUiModel(
    id = id,
    title = title,
    cover = cover,
    coverSmall = coverSmall,
    coverBig = coverBig,
    coverXl = coverXl,
    label = label,
    releaseDate = releaseDate?.formatDate(),
    genresUiModels = genres?.map { genreDataModel -> genreDataModel.toGenreUiModel() } ?: listOf(),
    contributorsUiModel = contributors?.map { contributorDataModel -> contributorDataModel.toContributorUiModel() }
        ?: listOf()
)

