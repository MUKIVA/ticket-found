plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.androidDefault)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.secrets)
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

    buildFeatures {
        buildConfig = true
    }
    // The general configuration is stored in the file:
    // "rootProject/build-logic/src/main/AndroidDefaultPlugin"
}

dependencies {
    implementation(projects.ticketfoundUikit)

    implementation(projects.ticketfoundNavigation)
    implementation(projects.ticketfoundApi)
    implementation(projects.ticketfoundData)

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

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.json)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}

secrets {
    // Change the properties file from the default "local.properties" in your root project
    // to another properties file in your root project.
    propertiesFileName = "secrets.properties"

    // A properties file containing default secret values. This file can be checked in version
    // control.
    defaultPropertiesFileName = "secrets.defaults.properties"
}