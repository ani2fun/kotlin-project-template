package com.example.development

import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    id("com.example.common.java-base")

    // Classes annotated with @Configuration, @Controller, @RestController, @Service or @Repository are automatically opened
    // https://kotlinlang.org/docs/reference/compiler-plugins.html#spring-support
    // https://plugins.gradle.org/plugin/org.jetbrains.kotlin.plugin.spring
    kotlin("plugin.spring")

    // Allows to package executable jar or war archives, run Spring Boot applications, and use the dependency management
    // https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/
    id("org.springframework.boot")

    kotlin("plugin.jpa")
}

// Kotlin Spring compiler plugin requires "kotlin-allopen" in it's class path hence
logger.lifecycle("Enabling Kotlin Spring plugin in module ${project.path}")
apply(plugin = "org.jetbrains.kotlin.plugin.spring")

logger.lifecycle("Enabling Spring Boot plugin in module ${project.path}")
apply(plugin = "org.springframework.boot")

logger.lifecycle("Enabling Spring Boot Dependency Management in module ${project.path}")
apply(plugin = "io.spring.dependency-management")

logger.lifecycle("Enabling Spring Boot JPA in module ${project.path}")
apply(plugin = "org.jetbrains.kotlin.plugin.jpa")

springBoot {
    // Creates META-INF/build-info.properties for Spring Boot Actuator
    buildInfo()
}

// Disable default tasks implemented by spring-boot plugins. Please Enable tasks in specific projects.
// Todo: refactor to configure it for specific projects or use separate plugins and enable in there.
tasks.withType<BootJar> {
    logger.lifecycle("Configuring $name in project ${project.name}.")
    enabled = false
}

tasks.withType<Jar> {
    logger.lifecycle("Configuring $name in project ${project.name}.")
    enabled = false
}

tasks.withType<BootRun> {
    logger.lifecycle("Configuring $name in project ${project.name}.")
    enabled = false
}
