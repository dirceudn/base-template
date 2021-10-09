package com.org.albumdetail.model

import com.org.core.albumdetail.data.model.DeezerAlbumDetailDataModel
import com.org.core.albumdetail.data.model.GenreDataModel
import com.org.core.common.formatDate


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
        val genresUiModels: List<GenreUiModel>?
    ) : AlbumDetailUiState()

    object AlbumNotFound : AlbumDetailUiState()
    object AlbumLoading : AlbumDetailUiState()
    object AlbumEmpty : AlbumDetailUiState()
}

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
    genresUiModels = genres?.map { genreDataModel -> genreDataModel.toGenreUiModel() } ?: listOf()
)

