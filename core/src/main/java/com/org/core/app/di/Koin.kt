package com.org.core.app.di

import com.org.core.BuildConfig
import com.org.core.home.homeModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoinDependencies(
    isDebug: Boolean,
    specificModules: List<Module>,
    appDeclaration: KoinAppDeclaration = {},
) =
    startKoin {
        appDeclaration()
        modules(homeModule(BuildConfig.DEEZER_BASE_URL, isDebug) + specificModules)
    }