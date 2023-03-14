package com.example.standalone

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

dependencies {
    constraints {
        // Define dependency versions as constraints
        implementation("org.apache.commons:commons-text:1.9")
    }
}
