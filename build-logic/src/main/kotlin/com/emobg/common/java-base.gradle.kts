package com.example.common

import com.example.constants.CHARACTER_ENCODING
import com.example.constants.MAVEN_ARTIFACTORY_URL
import com.example.constants.JDK_VERSION

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()

    // Use Embog maven central for resolving dependencies.
    maven(MAVEN_ARTIFACTORY_URL) {
        credentials.username = System.getenv("ARTIFACTORY_READ_USERNAME")
        credentials.password = System.getenv("ARTIFACTORY_READ_PASSWORD")
    }
}

java {
    // Auto JDK setup
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(JDK_VERSION))
    }
}

tasks.named<Jar>("jar") {
    enabled = false
}

tasks.withType<JavaCompile> {
    options.encoding = CHARACTER_ENCODING
    // See: https://docs.oracle.com/en/java/javase/12/tools/javac.html
    options.compilerArgs.addAll(
        listOf(
            "-Xlint:all", // Enables all recommended warnings.
            "-Werror", // Terminates compilation when warnings occur.
        ),
    )
}
