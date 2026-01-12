pluginManagement {
    includeBuild("build-logic")

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
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "submanager"
include(":app")

include(":data:local")

include(":domain:local")

include(":core:designsystem")
include(":core:common")

include(":feature:main")
include(":feature:home")
include(":feature:setting")
include(":feature:subscription-add")
include(":feature:subscription-detail")
include(":feature:subscription-edit")

