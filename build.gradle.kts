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
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
}