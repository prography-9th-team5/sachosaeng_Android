pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = java.net.URI("https://devrepo.kakao.com/nexus/content/groups/public/") }
    }
}

rootProject.name = "sachosaeng"
include(":app")
include(":data")
include(":core")
include(":core:ui")
include(":core:util")
include(":feature:home")
include(":feature:mypage")
include(":feature:webview")
include(":feature:signup")
include(":feature:splash")
include(":feature:vote")
include(":feature:auth")
include(":core:usecase")
include(":core:model")
