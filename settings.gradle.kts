dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "KBase"
include(":app")
include(":presentation")
include(":core")
include(":presentation:ui")
include(":presentation:home")
include(":presentation:albumdetail")
include(":presentation:route")
