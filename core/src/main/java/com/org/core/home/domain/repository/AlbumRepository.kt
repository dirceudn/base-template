package com.org.core.home.domain.repository

import com.org.core.common.State
import com.org.core.home.data.model.DeezerAlbumDataModel
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun getAlbums()
    val deezerAlbumState: Flow<State<DeezerAlbumDataModel>>
}