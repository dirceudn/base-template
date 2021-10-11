package com.org.core.albumdetail.data.datasource

import com.org.core.albumdetail.data.model.DeezerAlbumDetailDataModel
import com.org.core.albumdetail.data.model.DeezerAlbumTracksDataModel
import com.org.core.common.DefaultErrorDataModel
import com.org.core.common.Either

interface AlbumDetailDataSource {
    suspend fun getAlbum(id: Long): Either<DefaultErrorDataModel, DeezerAlbumDetailDataModel>
    suspend fun getTrackFromAlbum(id: Long): Either<DefaultErrorDataModel, DeezerAlbumTracksDataModel>
}