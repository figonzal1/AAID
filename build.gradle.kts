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

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.0.0" apply false
    id("com.android.library") version "8.0.0" apply false

    id("org.jetbrains.kotlin.android") version "1.8.10" apply false

    id("com.google.gms.google-services") version "4.3.15" apply false

    id("com.google.firebase.crashlytics") version "2.9.5" apply false
    id("com.google.firebase.firebase-perf") version "1.4.2" apply false

    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1"

    id("org.sonarqube") version "4.0.0.2929"
}