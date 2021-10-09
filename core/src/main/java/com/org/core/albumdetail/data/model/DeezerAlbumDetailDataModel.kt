package com.org.core.albumdetail.data.model

import com.org.core.albumdetail.api.model.DeezerAlbumDetailResponse

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
    val genres: List<GenreDataModel>?
)

data class GenreDataModel(
    val id: Long?,
    val name: String?,
    val picture: String?,
    val type: String?
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
    genres = genre.data?.map { genreData -> genreData.toGenreDataModel() } ?: listOf()
)

fun DeezerAlbumDetailResponse.Genre.GenreData.toGenreDataModel() = GenreDataModel(
    id = id,
    name = name,
    picture = picture,
    type = type
)