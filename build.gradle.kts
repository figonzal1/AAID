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
 * Module: AAID
 * Last modified: 13-01-23 15:28
 */

buildscript {

    dependencies {

        //FIREBASE CRASH ANALYTICS
        classpath("com.google.gms:google-services:4.3.14")

        //FIREBASE CRASHLYTICS
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.2")

        //FIREBASE PERFORMANCE
        classpath("com.google.firebase:perf-plugin:1.4.2")

        //Google maps secrets
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.0" apply false
    id("com.android.library") version "7.4.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("org.sonarqube") version "3.5.0.2730"
}