package com.org.core.albumdetail.domain.usecase

import com.org.core.albumdetail.data.model.DeezerAlbumTracksDataModel
import com.org.core.albumdetail.domain.repository.AlbumDetailRepository
import com.org.core.common.State
import kotlinx.coroutines.flow.Flow


interface GetAlbumTrackUseCase {
    suspend fun invoke(): Flow<State<DeezerAlbumTracksDataModel>>
}

fun getAlbumTrackUseCase(albumRepository: AlbumDetailRepository): GetAlbumTrackUseCase =
    object : GetAlbumTrackUseCase {
        override suspend fun invoke(): Flow<State<DeezerAlbumTracksDataModel>> =
            albumRepository.deezerAlbumTrackState
    }