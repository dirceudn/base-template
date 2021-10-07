package com.org.core.common

import io.ktor.client.features.*
import io.ktor.client.statement.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer


suspend inline fun <reified T> launchRequest(init: LaunchRequest.() -> Unit) =
    LaunchRequest().apply(init).launchRequestSerialize<T>()

class LaunchRequest {
    var errorFactory: (suspend (String) -> DefaultErrorDataModel)? = null
    lateinit var request: suspend () -> HttpResponse

    suspend inline fun <reified T> launchRequestSerialize(): Either<DefaultErrorDataModel, T> {
        return Either.catch {
            request()
        }.mapLeft { throwable: Throwable ->
            throwable.toDataModel()
        }
            .flatMap { response ->
                val responseString = response.readText()
                try {
                    if (response.status.value in 200..299) {
                        val result: T =
                            getJsonSerializer().decodeFromString(serializer(), responseString)
                        Either.Right(result)
                    } else {
                        when (response.status.value) {
                            in 300..399 -> Either.Left(
                                DefaultErrorEntity.Network(
                                    throwable = RedirectResponseException(
                                        response = response,
                                        cachedResponseText = responseString
                                    )
                                )
                            )
                            in 400..499 ->
                                errorFactory?.let {
                                    Either.Left(it(responseString))
                                } ?: Either.Left(
                                    DefaultErrorEntity.Network(
                                        throwable = ClientRequestException(response = response, cachedResponseText = responseString)
                                    )
                                )
                            in 500..599 -> Either.Left(
                                DefaultErrorEntity.Network(
                                    throwable = ServerResponseException(response = response, cachedResponseText = responseString)
                                )
                            )
                            else -> Either.Left(DefaultErrorEntity.Unknown(throwable = null))
                        }
                    }
                } catch (e: Exception) {
                    Either.Left(DefaultErrorEntity.Network(throwable = e))
                }
            }
    }
}

fun NetworkErrorNetworkModel.toDataModel(errorFactory: ((NetworkErrorNetworkModel) -> DefaultErrorDataModel?)?): DefaultErrorDataModel =
    errorFactory?.invoke(this) ?: DefaultErrorEntity.Network(throwable = null)


fun <T : Throwable> T.toDataModel(): DefaultErrorDataModel {
    return try {
        when (this) {
            is RedirectResponseException -> DefaultErrorEntity.Network(throwable = this)
            is ClientRequestException -> DefaultErrorEntity.Network(throwable = this)
            is ServerResponseException -> DefaultErrorEntity.Network(throwable = this)
            is ResponseException -> DefaultErrorEntity.Network(throwable = this)
            is IllegalArgumentException -> DefaultErrorEntity.Network(throwable = this)
            else -> DefaultErrorEntity.Network(throwable = this)
        }
    } catch (e: SerializationException) {
        DefaultErrorEntity.Unknown(throwable = e)
    }
}

fun getJsonSerializer() = Json(from = Json) {
    ignoreUnknownKeys = true
    coerceInputValues = true
    isLenient = true
}