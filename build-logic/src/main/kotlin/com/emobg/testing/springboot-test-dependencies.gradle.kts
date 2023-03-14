package com.example.testing

plugins {
    id("com.example.development.springboot-plugins-base")
}

dependencies {
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")
}
