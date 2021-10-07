package com.org.core.home.domain.usecase

import com.org.core.home.domain.repository.AlbumRepository

interface GetAlbumFlowUseCase {
    suspend fun invoke()
}

fun albumFlowUseCase(albumRepository: AlbumRepository): GetAlbumFlowUseCase =
    object : GetAlbumFlowUseCase {
        override suspend fun invoke() = albumRepository.getAlbums()
    }