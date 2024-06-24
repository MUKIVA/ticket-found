@file:Suppress("UnstableApiUsage")

include(":ticketfound-data")



enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Ticket-Found"

includeBuild("build-logic")

include(":app")

include(":ticketfound-api")
include(":ticketfound-navigation")
include(":ticketfound-uikit")

include(":feature:subscribes")
include(":feature:profile")
include(":feature:shortcut")
include(":feature:hotels")
include(":feature:airtickets")
include(":feature:main")

 