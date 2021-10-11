package com.org.albumdetail.di

import com.org.albumdetail.viewmodel.AlbumDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val albumDetailModule = module {

    viewModel { AlbumDetailViewModel(get(), get(), get(), get()) }

}