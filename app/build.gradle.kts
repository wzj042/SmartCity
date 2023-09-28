@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "cn.xqher.smartcity"
    compileSdk = 34

    defaultConfig {
        applicationId = "cn.xqher.smartcity"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

//    splash
//    noinspection UseTomlInstead
    implementation("androidx.core:core-splashscreen:1.0.1")


//    preference
//    noinspection UseTomlInstead
    implementation("androidx.datastore:datastore-preferences:1.0.0")

//    navigation
//    noinspection UseTomlInstead
    implementation("androidx.navigation:navigation-compose:2.7.3")

//    banner
//    noinspection UseTomlInstead
    implementation("com.github.zhujiang521:Banner:2.6.5")

//    coil
//    noinspection UseTomlInstead
    implementation("io.coil-kt:coil-compose:2.4.0")

//    for net with kt-coroutines, okhttp, gson, compose.runtime

//    noinspection UseTomlInstead
    implementation("com.google.code.gson:gson:2.10.1")

//    noinspection UseTomlInstead
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3") // 协程(版本自定)

//    noinspection UseTomlInstead
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

//    noinspection UseTomlInstead
    implementation("com.github.liangjingkanji:Net:3.6.2")

//    noinspection UseTomlInstead
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

//    Tabs
    implementation("androidx.compose.foundation:foundation:1.5.2")
//    def dependencies
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(platform(libs.compose.bom))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(platform(libs.compose.bom))
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}