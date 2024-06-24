plugins {
    alias(libs.plugins.androidLib)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.androidDefault)
    alias(libs.plugins.kotlinxSerialization)
}

android {
    namespace = "com.github.mukiva.ticketfound.api"
}

dependencies {

    implementation(libs.retrofit)
    implementation(libs.retrofit.adapter.result)
    implementation(libs.retrofit.converter.json)
    implementation(libs.okhttp.logging)
    implementation(libs.okhttp)

    implementation(libs.kotlinx.coroutines)

}