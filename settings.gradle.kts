pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven(url = "https://central.sonatype.com/repository/maven-snapshots/") {
            content {
                includeModule("net.minestom", "minestom")
                includeModule("net.minestom", "testing")
            }
        }
        mavenCentral()
    }
}

rootProject.name = "Minestom173"