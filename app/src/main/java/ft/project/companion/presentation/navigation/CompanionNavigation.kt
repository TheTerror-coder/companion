package ft.project.companion.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ft.project.companion.presentation.composables.authentication.AuthenticationScreen
import ft.project.companion.presentation.composables.authentication.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object Authentication
@Serializable
object Home


@Composable
fun CompanionNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Authentication) {
        composable<Authentication> { AuthenticationScreen(navController = navController) }
        composable<Home> { LoginScreen(navController = navController) }
    }
}