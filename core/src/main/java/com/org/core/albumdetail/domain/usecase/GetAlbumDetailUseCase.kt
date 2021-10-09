package com.org.core.albumdetail.domain.usecase

import com.org.core.albumdetail.data.model.DeezerAlbumDetailDataModel
import com.org.core.albumdetail.domain.repository.AlbumDetailRepository
import com.org.core.common.State
import kotlinx.coroutines.flow.Flow

interface GetAlbumDetailUseCase {
    suspend fun invoke(): Flow<State<DeezerAlbumDetailDataModel>>
}

fun getAlbumDetailUseCase(albumRepository: AlbumDetailRepository): GetAlbumDetailUseCase =
    object : GetAlbumDetailUseCase {
        override suspend fun invoke(): Flow<State<DeezerAlbumDetailDataModel>> =
            albumRepository.deezerAlbumDetailState
    }