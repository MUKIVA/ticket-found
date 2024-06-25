plugins {
    alias(libs.plugins.androidLib)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.androidDefault)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.github.mukiva.feature.airtickets"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(projects.ticketfoundUikit)
    implementation(projects.ticketfoundData)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.fragment)
    implementation(libs.material)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.adapter.delegates)
    implementation(libs.adapter.delegates.viewbinding)

    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment)
}