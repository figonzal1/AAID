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
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
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
        versionCode = 4
        versionName = "1.2"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.activity:activity-compose:1.7.0")

    implementation("androidx.compose.material3:material3:1.0.1") // Material 3
    implementation("androidx.core:core-splashscreen:1.0.0") //splash screen

    //Compose
    implementation("androidx.compose.ui:ui:1.4.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.1")
    implementation("androidx.compose.material:material-icons-extended:1.4.1")

    //Compose navigation
    implementation("androidx.navigation:navigation-compose:2.5.3")

    //Animation
    implementation("com.google.accompanist:accompanist-navigation-animation:0.28.0")

    //AAID
    implementation("com.google.android.gms:play-services-ads-identifier:18.0.1")

    //LIFECYCLE
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    //GOOGLE PLAY
    implementation("com.google.android.gms:play-services-ads:22.0.0")

    //FIREBASE BOM
    implementation(platform("com.google.firebase:firebase-bom:31.1.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-perf-ktx")

    //TIMBER
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("androidx.test.uiautomator:uiautomator:2.2.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:truth:1.5.0")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.1")
    androidTestImplementation("androidx.navigation:navigation-testing:2.5.3")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")
    androidTestImplementation("tools.fastlane:screengrab:2.1.1")

    //debugImplementation("com.squareup.leakcanary:leakcanary-android:2.9.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.1")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.1")
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