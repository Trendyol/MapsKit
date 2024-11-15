dependencyResolutionManagement {
    versionCatalogs {
        create("libs") { from(files("gradle/dependencies/libs.toml")) }
        create("plugin") { from(files("gradle/dependencies/plugin.toml")) }
    }
}