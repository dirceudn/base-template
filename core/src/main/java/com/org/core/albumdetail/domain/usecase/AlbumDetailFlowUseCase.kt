package com.org.core.albumdetail.domain.usecase

import com.org.core.albumdetail.domain.repository.AlbumDetailRepository

interface AlbumDetailFlowUseCase {
    suspend fun invoke(id: Long)
}

fun albumDetailFlowUseCase(albumRepository: AlbumDetailRepository): AlbumDetailFlowUseCase =
    object : AlbumDetailFlowUseCase {
        override suspend fun invoke(id: Long) = albumRepository.getAlbum(id = id)
    }