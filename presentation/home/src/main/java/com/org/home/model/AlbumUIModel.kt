package com.org.home.model

import com.org.core.home.data.model.AlbumDataModel
import com.org.core.home.data.model.DeezerAlbumDataModel


sealed class AlbumCollectionState {
    data class AlbumsCollection(val albums: List<AlbumUIModel>?) : AlbumCollectionState()

    data class AlbumUIModel(
        val id: Long?,
        val title: String?,
        val artistName: String?,
        val cover: String?,
        val coverSmall: String?,
        val coverBig: String?
    ) : AlbumCollectionState()

    object AlbumCollectionLoading : AlbumCollectionState()
    object AlbumCollectionEmpty : AlbumCollectionState()
    object AlbumCollectionLoadError : AlbumCollectionState()
}


fun DeezerAlbumDataModel.mapAlbumCollectionUiModel() = AlbumCollectionState.AlbumsCollection(
    albums = this.albums.map { album ->
        album.toAlbumUiMode()
    }
)

fun AlbumDataModel.toAlbumUiMode() = AlbumCollectionState.AlbumUIModel(
    id = id,
    title = title,
    artistName = artistName,
    cover = coverMedium,
    coverBig = coverBig,
    coverSmall = coverSmall
)