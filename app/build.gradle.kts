plugins {
    id(GradlePlugin.ANDROID_APPLICATION)
    id(GradlePlugin.KOTLIN_ANDROID)
    id(GradlePlugin.KAPT)
    id(GradlePlugin.DAGGER_HILT)
}

android {
    compileSdk = 33
    namespace = "com.ilhomsoliev.testappognam"
    defaultConfig {
        applicationId = "com.ilhomsoliev.testappognam"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "1.8"

    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Android
    implementation(Dependencies.android.lifecycleRuntime)
    implementation(Dependencies.android.navigationRuntime)
    implementation(Dependencies.android.dataStore)
    implementation(Dependencies.android.lifecycleViewmodel)
    implementation(Dependencies.android.ktx)
    implementation(Dependencies.android.material)
    implementation(Dependencies.android.activityCompose)
    // Hilt
    implementation(Dependencies.android.hilt.android)
    kapt(Dependencies.android.hilt.androidCompiler)
    kapt(Dependencies.android.hilt.compiler)
    implementation(Dependencies.android.hilt.navigation)
    // Room
    implementation(Dependencies.android.room.ktx)
    implementation(Dependencies.android.room.runtime)
    kapt(Dependencies.android.room.compiler)
    implementation(Dependencies.android.room.paging)
    // Paging
    implementation(Dependencies.paging.compose)
    implementation(Dependencies.paging.runtime)
    // Compose
    implementation(Dependencies.compose.icons)
    implementation(Dependencies.compose.material)
    implementation(Dependencies.compose.activity)
    implementation(Dependencies.compose.navigation)
    implementation(Dependencies.compose.constraintLayout)
    implementation(Dependencies.compose.uiToolingPreview)
    implementation(Dependencies.compose.ui)
    implementation(Dependencies.compose.uiTest)
    // Material 3
    implementation("androidx.compose.material3:material3")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    // Test
    implementation(Dependencies.test.core)
    implementation(Dependencies.test.coreKtx)
    implementation(Dependencies.test.junit)
    // Accompanist
    implementation(Dependencies.accompanist.animation)
    implementation(Dependencies.accompanist.flowRow)
    implementation(Dependencies.accompanist.systemUiController)
    // DataStore
    implementation(Dependencies.android.dataStore)
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.0")
    // Ktor
    implementation(Dependencies.network.ktor.core)
    implementation(Dependencies.network.ktor.cio)
    implementation(Dependencies.network.ktor.serialization)
    implementation(Dependencies.network.ktor.websockets)
    implementation(Dependencies.network.ktor.logging)
    implementation("ch.qos.logback:logback-classic:1.2.6")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation(Dependencies.imageLoader.compose)
    implementation(Dependencies.imageLoader.gif)

}