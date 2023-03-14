

plugins {
    id("com.example.testing.test-base")
}

val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    // This dependency is not managed by spring-boot. Hence we have specied it version in versionCatalog.
    testImplementation(kotlin("test"))
    add("implementation", versionCatalog.findLibrary("cucumber-java").get())
    add("implementation", versionCatalog.findLibrary("cucumber-junit").get())
    add("implementation", versionCatalog.findLibrary("cucumber-reporting").get())
}
