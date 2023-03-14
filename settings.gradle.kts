// Plugin Management should be the first block in this file.
pluginManagement {
    // Include 'plugins build' to define convention plugins.
    includeBuild("build-logic")
}

// Note: This is a standalone project.
includeBuild("acceptance-test")

include("app", "domain")
rootProject.name = "kotlin-project-template"
