package com.org.core.albumdetail.api.network

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class DeezerAlbumDetailApi(private val clientFactory: () -> HttpClient) {

    private val client by lazy {
        clientFactory()
    }

    suspend fun fetchAlbumDetail(albumId: Long) = client.get<HttpResponse> {
        url {
            encodedPath = "/album/${albumId}"
        }
    }

    suspend fun fetchTracksFromAlbum(albumId: Long) = client.get<HttpResponse> {
        url {
            encodedPath = "/album/${albumId}/tracks"
        }
    }
}