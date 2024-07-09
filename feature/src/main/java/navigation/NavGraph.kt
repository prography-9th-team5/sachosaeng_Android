package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sachosaeng.feature.home.HomeScreen
import com.example.sachosaeng.feature.splash.SplashScreen
import com.example.sachosaeng.feature.vote.VoteScreen

@Composable
fun addNavGraph(navController: NavHostController) {
    BottomNavGraph(navController = navController)
}

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SPLASH.route
    ) {
        composable(Screen.SPLASH.route) {
            SplashScreen(navigateToMain = { navController.navigate(Screen.HOME.route) })
        }
        composable(Screen.HOME.route) {
            HomeScreen()
        }
        composable(Screen.VOTE.route) {
            VoteScreen(navigateToBackStack = { navController.popBackStack() })
        }
    }
}
