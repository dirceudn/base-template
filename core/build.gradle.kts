plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(AndroidSdk.compile)
    buildToolsVersion = AndroidSdk.buildToolsVersion

    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)


        testInstrumentationRunner = AndroidSdk.testRunner
    }

    flavorDimensions("full")
    productFlavors {
        create("dev") {
            buildConfigField("String", "DEEZER_BASE_URL", "\"api.deezer.com/2.0\"")
        }
        create("prod") {
            buildConfigField("String", "DEEZER_BASE_URL", "\"api.deezer.com/2.0\"")
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

    api(Courotinues.core)

    // Ktor
    api(Ktor.core)
    api(Ktor.json)
    api(Ktor.serialisation)
    api(Ktor.clientLogging)
    api(Ktor.androidClient)

    // Serialize
    api(Koin.core)
    api(Koin.android)
    // Serialize
    api(KotlinXSerialisation.serialisation)
    api(KotlinXSerialisation.json)

    // date time
    api(DateTime.dateTime)

    // Logs
    api(Logs.napier)
}