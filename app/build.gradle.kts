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
        enableAggregatingTask = false
        disableCrossCompilationRootValidation = false
        enableExperimentalClasspathAggregation = false
    }

    // The general configuration is stored in the file:
    // "rootProject/build-logic/src/main/AndroidDefaultPlugin"
}

dependencies {
    implementation(projects.ticketfoundUikit)

    implementation(projects.ticketfoundNavigation)

    implementation(projects.feature.main)
    implementation(projects.feature.airtickets)
    implementation(projects.feature.hotels)
    implementation(projects.feature.profile)
    implementation(projects.feature.shortcut)
    implementation(projects.feature.subscribes)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}