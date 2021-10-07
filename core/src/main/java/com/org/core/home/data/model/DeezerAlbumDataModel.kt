package com.org.core.home.data.model

import com.org.core.home.api.model.AlbumData
import com.org.core.home.api.model.DeezerAlbumResponse

data class DeezerAlbumDataModel(val albums: List<AlbumDataModel>)

data class AlbumDataModel(val id: Long?)

fun DeezerAlbumResponse.toDataModel() = DeezerAlbumDataModel(
    albums = this.data.map { albums -> albums.toAlbumDataModel() }
)

fun AlbumData.toAlbumDataModel() = AlbumDataModel(
    id = this.id
)