package com.org.core.albumdetail.domain.repository

import com.org.core.albumdetail.data.model.DeezerAlbumDetailDataModel
import com.org.core.common.State
import kotlinx.coroutines.flow.Flow

interface AlbumDetailRepository {
    suspend fun getAlbum(id: Long)
    val deezerAlbumDetailState: Flow<State<DeezerAlbumDetailDataModel>>

}