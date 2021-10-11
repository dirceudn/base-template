package com.org.core.albumdetail.domain.repository

import com.org.core.albumdetail.data.model.DeezerAlbumDetailDataModel
import com.org.core.albumdetail.data.model.DeezerAlbumTracksDataModel
import com.org.core.common.State
import kotlinx.coroutines.flow.Flow

interface AlbumDetailRepository {
    suspend fun getAlbum(id: Long)
    val deezerAlbumDetailState: Flow<State<DeezerAlbumDetailDataModel>>
    val deezerAlbumTrackState: Flow<State<DeezerAlbumTracksDataModel>>

    suspend fun getTracksFromAlbum(id: Long)
}