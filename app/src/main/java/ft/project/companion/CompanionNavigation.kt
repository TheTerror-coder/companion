package ft.project.companion

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ft.project.companion.presentation.authentication.AuthenticationScreen
import ft.project.companion.presentation.authentication.AuthenticationUiAction
import ft.project.companion.presentation.home.HomeScreen
import ft.project.companion.presentation.authentication.AuthenticationState
import kotlinx.serialization.Serializable

@Serializable
object AuthenticationRoute
@Serializable
object HomeRoute

@Composable
fun CompanionNavigation(
    authUiState: AuthenticationState,
    onAuthUiAction: (AuthenticationUiAction) -> Unit,
    onFortyTwoShieldClick: () -> Unit,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AuthenticationRoute) {
        composable<AuthenticationRoute> { AuthenticationScreen(
            navController = navController,
            authUiState = authUiState,
            onAuthUiAction = onAuthUiAction,
            onFortyTwoShieldClick = onFortyTwoShieldClick
        ) }
        composable<HomeRoute> { HomeScreen(navController = navController) }
    }
}

