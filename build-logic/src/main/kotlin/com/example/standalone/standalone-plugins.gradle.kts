package com.example.standalone

import com.gorylenko.GenerateGitPropertiesTask
import com.gorylenko.GitPropertiesPluginExtension
import de.jansauer.printcoverage.PrintCoverageTask

plugins {

    id("com.gorylenko.gradle-git-properties")
    id("org.owasp.dependencycheck")
    id("de.jansauer.printcoverage")
    id("org.sonarqube")
    id("org.jlleitschuh.gradle.ktlint")
}

// --- generateGitProperties ---
configure<GitPropertiesPluginExtension>() {
    this.dateFormat = "yyyy-MM-dd'T'HH:mmZ"
    this.dateFormatTimeZone = "PST"
    // this.gitPropertiesDir = project.layout.projectDirectory() // todo
}

tasks.named<GenerateGitPropertiesTask>("generateGitProperties") {
    enabled = false
}

// --- dependencycheck ---
// TODO: update following config as per latest documentation
dependencyCheck {
    this.format = "ALL"
    this.failBuildOnCVSS = 7.0f
    this.suppressionFile = "${project.findProject(":build-logic")?.resources}/dependencycheck-suppressions.xml"

    // TODO: CHANGE OUTPUT DIR TO SUITABLE LOCATION. Currently it's in project app.
    this.outputDirectory = "${project.findProject(":app")?.buildDir}/security-report"
}

// --- printCoverage ---
tasks.named<PrintCoverageTask>("printCoverage") {
    this.coverageType.set("INSTRUCTION")
}

// --- sonarqube ----

// --- Ktlint ---

ktlint {
    filter {
        exclude("**/generated/**")
        include("*.kts", "*/*.kts", "**/kotlin/**")
    }
}
