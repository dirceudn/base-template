package com.org.kbase.app.di

import com.org.home.viewmodel.HomeViewModel
import com.org.route.OnAlbumDetailRouteContract
import com.org.route.OnAlbumRoute
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get(), get()) }

    factory<OnAlbumDetailRouteContract> { OnAlbumRoute() }
}