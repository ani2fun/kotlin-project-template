plugins {
    // Support convention plugins written in Kotlin.
    // Convention plugins are build scripts in 'src/main' that automatically become available as plugins in the main build.
    `kotlin-dsl`
}

repositories {
    // Use the plugin portal to apply community plugins in convention plugins.
    gradlePluginPortal()
    // Use the maven central portal to apply community plugins in convention plugins.
    mavenCentral()
}

val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {

    // These are dependencies required by plugins to pre-compile in "initialization" phase of gradle
    val list = listOf(
        "kotlin-gradle-plugin",
        "springboot-gradle-plugin",
        "kotlin-allopen",
        "kotlin-noarg",
        // Standalone plugins dependencies
        "git-properties",
        "dependency-check-gradle",
        "printcoverage",
        "snoarqube-plugin",
        "kordamp-jacoco-plugin",
        "ktlint-plugin",
        "org-openapi-generator",
    )

    list.map {
        add("implementation", versionCatalog.findLibrary(it).get())
    }
}
