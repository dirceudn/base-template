package com.org.core.albumdetail.api.network

import com.org.core.albumdetail.api.model.DeezerAlbumDetailResponse
import com.org.core.albumdetail.data.datasource.AlbumDetailDataSource
import com.org.core.albumdetail.data.model.AlbumDetailErrorNetworkModel
import com.org.core.albumdetail.data.model.toDataModel
import com.org.core.albumdetail.data.model.toDeezerAlbumTrackDataModel
import com.org.core.common.DefaultErrorEntity
import com.org.core.common.getJsonSerializer
import com.org.core.common.launchRequest
import com.org.core.home.api.model.TrackResponse

class NetworkAlbumDetailDataSource(private val deezerAlbumDetailApi: DeezerAlbumDetailApi) :
    AlbumDetailDataSource {

    override suspend fun getAlbum(id: Long) = launchRequest<DeezerAlbumDetailResponse> {
        errorFactory = { response ->
            val error =
                getJsonSerializer().decodeFromString(
                    AlbumDetailErrorNetworkModel.serializer(),
                    response
                )
            DefaultErrorEntity.NoDataFound(error.error.message)
        }
        request = {
            deezerAlbumDetailApi.fetchAlbumDetail(albumId = id)
        }
    }.map { albumResponse ->
        albumResponse.toDataModel()
    }

    override suspend fun getTrackFromAlbum(id: Long) = launchRequest<TrackResponse> {
        errorFactory = { response ->
            val error =
                getJsonSerializer().decodeFromString(
                    AlbumDetailErrorNetworkModel.serializer(),
                    response
                )
            DefaultErrorEntity.NoDataFound(error.error.message)
        }
        request = {
            deezerAlbumDetailApi.fetchTracksFromAlbum(albumId = id)
        }
    }.map { trackResponse ->
        trackResponse.toDeezerAlbumTrackDataModel()
    }
}