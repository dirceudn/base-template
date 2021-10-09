// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

        maven(url = "https://kotlin.bintray.com/kotlinx/")

    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath(kotlin("serialization", version = Versions.kotlin))


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.kts files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}