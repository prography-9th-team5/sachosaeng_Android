package navigation

enum class Screen(val route: String) {
    SPLASH(route = "splash_screen"),
    HOME(route = "home_screen"),
    BOOKMARK(route = "bookmark_screen"),
    VOTE(route = "vote_screen")
}