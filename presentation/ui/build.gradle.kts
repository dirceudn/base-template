plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdkVersion(AndroidSdk.compile)
    buildToolsVersion = AndroidSdk.buildToolsVersion

    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)


        testInstrumentationRunner = AndroidSdk.testRunner
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    flavorDimensions("full")
    productFlavors {
        create("dev") {
        }
        create("prod") {
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    api(Android.androidXlifeCycleViewModel)
    api(Android.androidXlifeCycleExtension)
    api(Compose.activity)
    api(Compose.ui)
    api(Compose.uiGraphics)
    api(Compose.uiTooling)
    api(Compose.foundationLayout)
    api(Compose.material)
    api(Compose.runtimeLiveData)
    api(Compose.runtime)
    api(Compose.foundation)
    api(Compose.navigation)
    api(Compose.constraintLayout)
    api(Koin.core)
    api(Koin.android)

    api(Android.coreKtx)
    api(Android.appCompat)
    api(Android.material)
    api(Accompanist.insets)
    api(Accompanist.coil)

    api(project(":core"))

}