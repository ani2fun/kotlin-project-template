package com.example.common

import com.example.constants.JDK_VERSION
import com.example.constants.KOTLIN_VERSION
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Apply this kotlin common convention plugin for shared build configuration between projects.
 * */

plugins {
    // Apply the java-library plugin for API and implementation separation.
    id("com.example.common.java-base")
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm")
}

kotlin {
    jvmToolchain {
        languageVersion.set(
            JavaLanguageVersion.of(JDK_VERSION),
        )
    }
}

val embeddedMajorAndMinorKotlinVersion = project.getKotlinPluginVersion().substringBeforeLast(".")
if (KOTLIN_VERSION != embeddedMajorAndMinorKotlinVersion) {
    logger.warn(
        "Constant 'KOTLIN_VERSION' ($KOTLIN_VERSION) differs from embedded Kotlin version in Gradle (${project.getKotlinPluginVersion()})!\n" +
            "Constant 'KOTLIN_VERSION' should be ($embeddedMajorAndMinorKotlinVersion).",
    )
}

tasks.withType<KotlinCompile> {
    logger.lifecycle("Configuring $name with version ${project.getKotlinPluginVersion()} in project ${project.name}")
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        allWarningsAsErrors = true
        jvmTarget = JDK_VERSION
        languageVersion = KOTLIN_VERSION
        apiVersion = KOTLIN_VERSION
    }
}

tasks.named<KotlinCompile>("compileTestKotlin") {
    logger.lifecycle("Configuring $name with version ${project.getKotlinPluginVersion()} in project ${project.name}")
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        allWarningsAsErrors = true
        jvmTarget = JDK_VERSION
        languageVersion = KOTLIN_VERSION
        apiVersion = KOTLIN_VERSION
    }
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    constraints {
        // Define dependency versions as constraints
        // Use the Kotlin JDK 8 standard library.
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }
}
