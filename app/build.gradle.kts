@file:Suppress("UnstableApiUsage")

buildscript {
    dependencies {
    }
}

plugins {
    id("com.android.application")
    kotlin("android")
}

baseConfig()
compose()

android {

    defaultConfig {
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

dependencies {
    androidBase()
    compose()
    dataBase()
    // ImageLoad
    implementation("com.github.skydoves:landscapist-glide:2.1.9")
    implementation("com.github.skydoves:landscapist-placeholder:2.1.9")
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.10")
}