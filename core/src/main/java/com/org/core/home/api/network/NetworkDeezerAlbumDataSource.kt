package com.org.core.home.api.network

import com.org.core.common.launchRequest
import com.org.core.home.api.model.DeezerAlbumResponse
import com.org.core.home.data.datasource.DeezerAlbumDataSource
import com.org.core.home.data.model.toDataModel

class NetworkDeezerAlbumDataSource(private val deezerAlbumApi: DeezerAlbumApi) :
    DeezerAlbumDataSource {

    override suspend fun getAlbum() = launchRequest<DeezerAlbumResponse> {
        request = {
            deezerAlbumApi.fetchAlbums()
        }
    }.map { albumResponse ->
        albumResponse.toDataModel()
    }

}