package com.org.core.albumdetail.domain.usecase

import com.org.core.albumdetail.domain.repository.AlbumDetailRepository


interface TrackAlbumFlowUseCase {
    suspend fun invoke(id: Long)
}

fun trackAlbumFlowUseCase(albumRepository: AlbumDetailRepository): TrackAlbumFlowUseCase =
    object : TrackAlbumFlowUseCase {
        override suspend fun invoke(id: Long) = albumRepository.getTracksFromAlbum(id = id)
    }