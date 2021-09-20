package com.org.core.common

import io.ktor.client.features.*
import kotlinx.serialization.SerializationException

typealias DefaultErrorDataModel = DefaultErrorEntity

fun DefaultErrorDataModel.toEntity(): DefaultErrorEntity = this

fun Throwable.toDataModel() = when (this) {
    is ClientRequestException -> DefaultErrorEntity.Network(this.message, this)
    is SerializationException ->
        DefaultErrorEntity.Unknown(throwable = this)
    else -> DefaultErrorEntity.Unknown(throwable = Exception(this))
}


fun checkInError(message: String) = DefaultErrorEntity.CheckInError(message)
fun bookingNotConfirmedError(message: String) =
    DefaultErrorEntity.PermissionNotConfirmedError(message)

