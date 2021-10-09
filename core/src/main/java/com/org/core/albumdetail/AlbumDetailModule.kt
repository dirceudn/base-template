package com.org.core.albumdetail

import com.org.core.albumdetail.api.network.DeezerAlbumDetailApi
import com.org.core.albumdetail.api.network.NetworkAlbumDetailDataSource
import com.org.core.albumdetail.data.datasource.AlbumDetailDataSource
import com.org.core.albumdetail.data.repository.AlbumDetailRepositoryImpl
import com.org.core.albumdetail.domain.repository.AlbumDetailRepository
import com.org.core.albumdetail.domain.usecase.AlbumDetailFlowUseCase
import com.org.core.albumdetail.domain.usecase.GetAlbumDetailUseCase
import com.org.core.albumdetail.domain.usecase.albumDetailFlowUseCase
import com.org.core.albumdetail.domain.usecase.getAlbumDetailUseCase
import com.org.core.clients.deezerAlbumClient
import org.koin.dsl.module

fun albumDetailDiModule(deezerBaseUrl: String, isDebug: Boolean) = module {
    single<DeezerAlbumDetailApi> {
        DeezerAlbumDetailApi { deezerAlbumClient(baseUrl = deezerBaseUrl, isDebug = isDebug) }
    }
}

val useCase = module {
    factory<AlbumDetailFlowUseCase> { albumDetailFlowUseCase(get()) }
    factory<GetAlbumDetailUseCase> { getAlbumDetailUseCase(get()) }

}

val dataSource = module {
    single<AlbumDetailDataSource> { NetworkAlbumDetailDataSource(get()) }
}
val repository = module {
    single<AlbumDetailRepository> { AlbumDetailRepositoryImpl(get()) }

}

fun albumDetailModule(deezerBaseUrl: String, isDebug: Boolean) =
    albumDetailDiModule(deezerBaseUrl, isDebug) + useCase + dataSource + repository

