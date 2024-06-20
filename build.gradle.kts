import plugins.compileSdk
import plugins.minSdk
import plugins.targetSdk

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.androidDefault) apply false
    alias(libs.plugins.androidBuildConfiguration)
}

buildscript {
    dependencies {
        classpath(libs.build.logic)
    }
}

buildConfiguration {
    minSdk(libs.versions.minSdk)
    targetSdk(libs.versions.targetSdk)
    compileSdk(libs.versions.compileSdk)
}