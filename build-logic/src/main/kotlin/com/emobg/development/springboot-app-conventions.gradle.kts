package com.example.development

import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    id("com.example.development.springboot-plugins-base")
}

// Enable BootyJar, BootRun and Jar generation whoever implements spring-app-conventions
tasks.withType<BootJar> {
    logger.lifecycle("Configuring $name in project ${project.name}.")
    enabled = true
}

tasks.withType<Jar> {
    logger.lifecycle("Configuring $name in project ${project.name}.")
    enabled = true
}

tasks.withType<BootRun> {
    logger.lifecycle("Configuring $name in project ${project.name}.")
    enabled = true
}

dependencies {
    // Runtime dependencies

    // Development dependencies
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Implementation dependencies
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    // Annotation processor dependencies
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}
