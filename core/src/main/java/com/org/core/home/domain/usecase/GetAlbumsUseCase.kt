package com.org.core.home.domain.usecase

import com.org.core.common.State
import com.org.core.home.data.model.DeezerAlbumDataModel
import com.org.core.home.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow

interface GetAlbumsUseCase {
    suspend fun invoke(): Flow<State<DeezerAlbumDataModel>>
}

fun getAlbumsUseCase(albumRepository: AlbumRepository): GetAlbumsUseCase =
    object : GetAlbumsUseCase {
        override suspend fun invoke(): Flow<State<DeezerAlbumDataModel>> =
            albumRepository.deezerAlbumState
    }