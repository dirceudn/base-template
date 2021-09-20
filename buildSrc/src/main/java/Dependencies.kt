object Versions{

    const val kotlin = "1.5.10"

    /**
     * Compose
     */
    const val compose = "1.0.0"
    const val composeTest = "1.0.0-rc02"
    const val composeActivity = "1.3.0"
    const val composeNavigation = "2.4.0-alpha05"
    const val composeConstraintLayout = "1.0.0-beta01"


    /**
     * Android native libs
     */

    const val accompanist = "0.15.0"
    const val lifecycle = "2.2.0"
    const val appCompat = "1.3.0"
    const val androidKtx = "1.6.0"
    const val constraintLayoutNative = "2.0.4"
    const val material = "1.4.0"
    const val firebaseBom = "26.1.0"
    const val AndroidXlifeCycle = "2.3.0"

    //- 3rd party lib
    const val koin = "3.0.2"
    const val kotlinxDateTime = "0.2.1"
    const val timber = "4.7.1"
    const val napier = "1.5.0"

    //tests
    const val junit = "4.13"
    const val testRunner = "1.3.0"
    //-kotlin jetbrains libs

    // network
    const val ktor = "1.6.1"
    const val kotlinx = "1.2.2"
    //courotines

    const val kotlinCoroutines = "1.4.3"
    //---

}

object Logs {
    const val napier = "io.github.aakira:napier:${Versions.napier}"
}

object Courotinues {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
}

object KotlinXSerialisation {
    const val serialisation =
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinx}"
    const val protobuf =
        "org.jetbrains.kotlinx:kotlinx-serialization-protobuf:${Versions.kotlinx}"
    const val json =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinx}"
}

object DateTime {
    const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDateTime}"
}

object Ktor {
    const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val json = "io.ktor:ktor-client-json:${Versions.ktor}"
    const val serialisation = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    const val androidClient = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val iosClient = "io.ktor:ktor-client-ios:${Versions.ktor}"
    const val clientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
}

object Test {
    const val jUnit = "junit:junit:${Versions.junit}"
    const val runner = "androidx.test:runner:${Versions.testRunner}"
    const val composeTest = "androidx.compose.ui:ui-test-junit4:${Versions.composeTest}}"
}

object Android {
    const val coreKtx = "androidx.core:core-ktx:${Versions.androidKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val androidXlifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidXlifeCycle}"
    const val androidXlifeCycleExtension = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidXlifeCycle}"
}

object Compose {
    const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val activity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayout}"
}

object LifeCycle {
    const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val livedataExt = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifeCycleService = "androidx.lifecycle:lifecycle-service:${Versions.lifecycle}"
    const val viewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

}

object Accompanist {
    const val accomPanist = "com.google.accompanist:accompanist-permissions:${Versions.accompanist}"
    const val insets = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"

}

object Koin {
    const val core = "io.insert-koin:koin-core:${Versions.koin}"
    const val android = "io.insert-koin:koin-android:${Versions.koin}"
}


object AndroidSdk {
    const val appId = "com.org.sweather"
    const val min = 21
    const val compile = 30
    const val target = compile
    const val versionName = "1.0"
    const val versionCode = 1
    const val buildToolsVersion = "30.0.3"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}