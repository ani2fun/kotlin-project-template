pluginManagement {
    // Include 'plugins build' to define convention plugins.
    includeBuild("../build-logic")
}

rootProject.name = "acceptance-test"

include("acceptance")
include("api-testing")
