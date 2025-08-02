import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Kotlin serialization plugin for type safe routes and navigation arguments
    kotlin("plugin.serialization") version "2.0.21"
    //hilt
    id("com.google.devtools.ksp") version "2.1.21-2.0.1"
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

// Creates a variable called keystorePropertiesFile, and initializes it to the
// keystore.properties file.
val keystorePropertiesFile = rootProject.file("keystore.properties")

// Initializes a new Properties() object called keystoreProperties.
val keystoreProperties = Properties()

// Loads the keystore.properties file into the keystoreProperties object.
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    namespace = "ft.project.companion"
    compileSdk = 35

    defaultConfig {
        applicationId = "ft.project.companion"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //customs
        manifestPlaceholders["appAuthRedirectScheme"] = "com.companion.jfaye"
        buildConfigField("String", "CLIENT_ID", "\"${keystoreProperties["CLIENT_ID"]}\"")
        buildConfigField("String", "CLIENT_SECRET", "\"${keystoreProperties["CLIENT_SECRET"]}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
        jvmTarget = "11"
        freeCompilerArgs = listOf("-XXLanguage:+PropertyParamAnnotationDefaultTargetMode")
    }
    buildFeatures {
        compose = true
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.material3)
    // Compose Material 3 Adaptive
    /**
     * Use the Material 3 adaptive library to create adaptive UIs that will adapt themselves
     * automatically according to the current window configurations like window size classes or
     * device postures. The library provides both default scaffold implementations and necessary
     * building block composables to create your own custom experiences.
     */
    implementation(libs.androidx.adaptive)
    implementation(libs.androidx.adaptive.layout)
    implementation(libs.androidx.adaptive.navigation)


    /**
     * ConstraintLayout is a layout that allows you to place composables relative to other
     * composables on the screen. It is an alternative to using multiple nested Row, Column,
     * Box and other custom layout elements. ConstraintLayout is useful when implementing larger
     * layouts with more complicated alignment requirements.
     */
    implementation(libs.androidx.constraintlayout)
    // To use constraintlayout in compose
    implementation(libs.androidx.constraintlayout.compose)

    implementation(libs.androidx.material3.window.size.class1)
    implementation(libs.androidx.material3.adaptive.navigation.suite)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(platform(libs.androidx.compose.bom.v20250500))
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.hilt.navigation.fragment)
    implementation(libs.androidx.hilt.navigation.compose)
    // Java language implementation
    implementation(libs.androidx.fragment)
    // Kotlin
    implementation(libs.androidx.fragment.ktx)
    // Jetpack Compose integration
    implementation(libs.androidx.navigation.compose)

    // Views/Fragments integration
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    // Feature module support for Fragments
    implementation(libs.androidx.navigation.dynamic.features.fragment)
    // Testing Navigation
    androidTestImplementation(libs.androidx.navigation.testing)
    // JSON serialization library, works with the Kotlin serialization plugin
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.appauth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // dagger/hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Preferences DataStore (SharedPreferences like APIs)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.preferences.core)
    // Typed DataStore (Typed API surface, such as Proto)
    implementation(libs.androidx.datastore.core)
    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.kotlinx.serialization)
    /**
     * An image loading library for Android and Compose Multiplatform. Coil is:
     *
     *     Fast: Coil performs a number of optimizations including memory and disk caching, downsampling the image, automatically pausing/cancelling requests, and more.
     *     Lightweight: Coil only depends on Kotlin, Coroutines, and Okio and works seamlessly with Google's R8 code shrinker.
     *     Easy to use: Coil's API leverages Kotlin's language features for simplicity and minimal boilerplate.
     *     Modern: Coil is Kotlin-first and interoperates with modern libraries including Compose, Coroutines, Okio, OkHttp, and Ktor.
     *
     * Coil is an acronym for: Coroutine Image Loader.
     * src: https://github.com/coil-kt/coil#jetpack-compose
     */
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}