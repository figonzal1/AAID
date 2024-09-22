/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package
 *
 * Author: Felipe González Alarcón
 * email: felipe.gonzalezalarcon94@gmail.com
 *
 * Copyright (c) 2024
 *
 * Project: AAID
 * Module: AAID
 * Last modified: 01-09-24 15:31
 */
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false

    //FIREBASE CRASH ANALYTICS
    alias(libs.plugins.com.google.gms.google.services) apply false

    //Crashlytics Gradle plugin
    alias(libs.plugins.com.google.firebase.crashlytics) apply false

    // Performance Monitoring plugin
    alias(libs.plugins.com.google.firebase.firebase.perf) apply false

    //Google maps secrets
    alias(libs.plugins.com.google.android.libraries.mapsplatform.secrets.gradle.plugin) apply false

    //Sonaqube
    alias(libs.plugins.org.sonarqube)

    //Version catalog updater
    alias(libs.plugins.com.github.ben.manes.versions)
    alias(libs.plugins.nl.littlerobots.version.catalog.update)
}

versionCatalogUpdate {
    // These options will be set as default for all version catalogs
    sortByKey.set(false)
}


// https://github.com/ben-manes/gradle-versions-plugin
tasks.withType<DependencyUpdatesTask> {
    resolutionStrategy {
        componentSelection {
            all {
                if (isNonStable(candidate.version) && !isNonStable(currentVersion)) {
                    reject("Release candidate")
                }
            }
        }
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}