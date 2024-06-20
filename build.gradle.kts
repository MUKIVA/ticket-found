import plugins.compileSdk
import plugins.minSdk
import plugins.targetSdk

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidDefault) apply false
    alias(libs.plugins.androidLib) apply false
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