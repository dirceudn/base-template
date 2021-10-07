package com.org.core.home

import com.org.core.clients.deezerAlbumClient
import com.org.core.home.api.network.DeezerAlbumApi
import com.org.core.home.api.network.NetworkDeezerAlbumDataSource
import com.org.core.home.data.datasource.DeezerAlbumDataSource
import com.org.core.home.data.repository.AlbumRepositoryImpl
import com.org.core.home.domain.repository.AlbumRepository
import com.org.core.home.domain.usecase.GetAlbumFlowUseCase
import com.org.core.home.domain.usecase.GetAlbumsUseCase
import com.org.core.home.domain.usecase.albumFlowUseCase
import com.org.core.home.domain.usecase.getAlbumsUseCase
import org.koin.dsl.module

fun homeDiModule(deezerBaseUrl: String, isDebug: Boolean) = module {
    single<DeezerAlbumApi> {
        DeezerAlbumApi { deezerAlbumClient(baseUrl = deezerBaseUrl, isDebug = isDebug) }
    }
}

val useCase = module {
    factory<GetAlbumFlowUseCase> { albumFlowUseCase(get()) }
    factory<GetAlbumsUseCase> { getAlbumsUseCase(get()) }
}

val repository = module {
    single<AlbumRepository> { AlbumRepositoryImpl(get()) }
}

val dataSource = module {
    single<DeezerAlbumDataSource> { NetworkDeezerAlbumDataSource(get()) }
}

fun homeModule(deezerBaseUrl: String, isDebug: Boolean) =
    homeDiModule(deezerBaseUrl, isDebug) + useCase + repository + dataSource