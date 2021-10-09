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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    flavorDimensions("full")

    productFlavors {
        create("dev") {

        }
        create("prod") {

        }
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
    api(project(":presentation:ui"))
    api(project(":presentation:home"))
    api(project(":presentation:albumdetail"))
    api(project(":presentation:route"))

}