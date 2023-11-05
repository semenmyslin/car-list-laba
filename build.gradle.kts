// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val kotlin_version by extra("1.9.0")

    dependencies {
        classpath("com.android.tools.build:gradle:8.1.0")
        classpath("com.google.gms:google-services:4.3.14")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }

}

plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false

}
