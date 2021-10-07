package com.org.home.model

import com.org.core.home.data.model.AlbumDataModel
import com.org.core.home.data.model.DeezerAlbumDataModel

data class AlbumsCollection(val albums: List<AlbumUIModel>)

data class AlbumUIModel(val id: Long?)

fun DeezerAlbumDataModel.mapAlbumCollectionUiModel() = AlbumsCollection(
    albums = this.albums.map { album ->
        album.toAlbumUiMode()
    }
)

fun AlbumDataModel.toAlbumUiMode() = AlbumUIModel(
    id = id
)