package com.org.core.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkErrorNetworkModel(
    @SerialName("error")
    val error: String
)

