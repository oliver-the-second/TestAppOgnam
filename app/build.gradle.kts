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
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.10")
}