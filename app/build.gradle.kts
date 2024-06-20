plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.androidDefault)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.github.mukiva.ticketfound"

    defaultConfig {
        applicationId = "com.github.mukiva.ticketfound"
        versionCode = 1
        versionName = "1.0"
    }

    hilt {
        enableAggregatingTask = true
        disableCrossCompilationRootValidation = false
        enableExperimentalClasspathAggregation = false
    }

    buildFeatures {
        viewBinding = true
    }

    // The general configuration is stored in the file:
    // "rootProject/build-logic/src/main/AndroidDefaultPlugin"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}