@file:Suppress("UnstableApiUsage")

buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
    }
}

plugins {
   // id("com.google.gms.google-services") version "4.3.15"
    id("com.android.application")
    id("androidx.navigation.safeargs")
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