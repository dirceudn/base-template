package com.org.core.clients

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

fun deezerAlbumClient(baseUrl: String, isDebug: Boolean) = HttpClient {
    val nonStrictJson = kotlinx.serialization.json.Json {
        isLenient = true
        ignoreUnknownKeys = true
        encodeDefaults = false
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer(nonStrictJson)
    }
    if (isDebug) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }
    defaultRequest {
        url.host = baseUrl
        url.protocol = URLProtocol.HTTPS
        header("Content-Type", "application/json")
        header("Accept", "application/json")
    }
}
