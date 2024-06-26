plugins {
    alias(libs.plugins.androidLib)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.androidDefault)
}

android {
    namespace = "com.github.mukiva.ticketfound.data"
}

dependencies {
    implementation(projects.ticketfoundApi)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.datastore)
}