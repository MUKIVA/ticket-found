plugins {
    alias(libs.plugins.androidLib)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.androidDefault)
}

android {
    namespace = "com.github.mukiva.ticketfound.uikit"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}