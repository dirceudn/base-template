package com.org.core.albumdetail.api.network

import com.org.core.albumdetail.api.model.DeezerAlbumDetailResponse
import com.org.core.albumdetail.data.datasource.AlbumDetailDataSource
import com.org.core.albumdetail.data.model.toDataModel
import com.org.core.common.launchRequest

class NetworkAlbumDetailDataSource(private val deezerAlbumDetailApi: DeezerAlbumDetailApi) :
    AlbumDetailDataSource {


    override suspend fun getAlbum(id: Long) = launchRequest<DeezerAlbumDetailResponse> {
        request = {
            deezerAlbumDetailApi.fetchAlbumDetail(albumId = id)
        }
    }.map { albumResponse ->
        albumResponse.toDataModel()
    }
}