/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package
 *
 * Author: Felipe González Alarcón
 * email: felipe.gonzalezalarcon94@gmail.com
 *
 * Copyright (c) 2023
 *
 * Project: AAID
 * Module: AAID.app
 * Last modified: 02-02-23 19:27
 */

import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.gms.google.services)
    alias(libs.plugins.com.google.firebase.crashlytics)
    alias(libs.plugins.com.google.firebase.firebase.perf)
    alias(libs.plugins.com.google.android.libraries.mapsplatform.secrets.gradle.plugin)
}

android {

    val prop = Properties().apply {
        load(FileInputStream(File(rootProject.rootDir, "keystore.properties")))
    }

    signingConfigs {
        create("aaidsign") {
            storeFile = file(prop.getProperty("storeFile"))
            storePassword = prop.getProperty("storePassword").toString()
            keyPassword = prop.getProperty("keyPassword").toString()
            keyAlias = prop.getProperty("keyAlias").toString()
        }
    }

    namespace = "cl.figonzal.aaid"
    compileSdk = 33
    buildToolsVersion = "33.0.2"

    defaultConfig {
        applicationId = "cl.figonzal.aaid"
        minSdk = 23
        targetSdk = 33
        versionCode = 5
        versionName = "1.2.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")

            resValue("string", "app_name", "AAID-debug")
            resValue("string", "ADMOB_ID_BANNER", "ca-app-pub-3940256099942544/6300978111")
        }

        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("aaidsign")

            resValue("string", "app_name", "AAID")
            resValue("string", "ADMOB_ID_BANNER", "ca-app-pub-6355378855577476/1471561473")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    flavorDimensions += listOf("version")
    productFlavors {
        create("dev") {
            dimension = "version"
            versionNameSuffix = "-dev"
        }
        create("prod") {
            dimension = "version"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.core.ktx)
    implementation(libs.androidx.activity.activity.compose)

    implementation(libs.androidx.core.core.splashscreen)

    //Compose
    implementation(libs.bundles.compose)

    //Compose navigation
    implementation(libs.androidx.navigation.navigation.compose)

    //Animation
    implementation(libs.com.google.accompanist.accompanist.navigation.animation)

    //AAID
    implementation(libs.bundles.google.play.ads)

    //LIFECYCLE
    implementation(libs.androidx.lifecycle.lifecycle.viewmodel.compose)

    //FIREBASE BOM
    implementation(platform(libs.com.google.firebase.firebase.bom))
    implementation(libs.bundles.firebase)

    //TIMBER
    implementation(libs.com.jakewharton.timber)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.ext.truth)
    androidTestImplementation(libs.androidx.test.espresso.espresso.core)
    androidTestImplementation(libs.androidx.test.espresso.espresso.intents)
    androidTestImplementation(libs.androidx.test.uiautomator)
    androidTestImplementation(libs.androidx.compose.ui.ui.test.junit4)
    androidTestImplementation(libs.androidx.navigation.navigation.testing)
    androidTestImplementation(libs.tools.fastlane.screengrab)

    //debugImplementation("com.squareup.leakcanary:leakcanary-android:2.9.1")
    debugImplementation(libs.androidx.compose.ui.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.ui.test.manifest)
}

sonarqube {
    properties {
        property("sonar.projectName", "AAID")
        property("sonar.projectKey", "AAID")
        property("sonar.test.inclusions", "**/*Test*/**")
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.sources", "src/main/java")
    }
}