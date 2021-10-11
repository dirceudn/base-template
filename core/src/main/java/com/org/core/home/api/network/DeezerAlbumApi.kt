package com.org.core.home.api.network

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
class DeezerAlbumApi(private val clientFactory: () -> HttpClient) {

    private val client by lazy {
        clientFactory()
    }

    suspend fun fetchAlbums() = client.get<HttpResponse> {
        url {
            encodedPath = "/user/2529/albums"
        }
    }
}