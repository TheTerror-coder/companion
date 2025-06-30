package ft.project.companion.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ft.project.companion.presentation.composables.AuthenticationScreen
import ft.project.companion.presentation.composables.AuthenticationUiAction
import ft.project.companion.presentation.composables.HomeScreen
import ft.project.companion.presentation.composables.LoginScreen
import ft.project.companion.presentation.viewmodels.AuthenticationState
import ft.project.companion.presentation.viewmodels.FortyTwoShieldViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable

@Serializable
object Authentication
@Serializable
object Home

@Composable
fun CompanionNavigation(
    authUiState: AuthenticationState,
    onAuthUiAction: (AuthenticationUiAction) -> Unit,
    onFortyTwoShieldClick: () -> Unit,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Authentication) {
        composable<Authentication> { AuthenticationScreen(
            navController = navController,
            authUiState = authUiState,
            onAuthUiAction = onAuthUiAction,
            onFortyTwoShieldClick = onFortyTwoShieldClick
        ) }
        composable<Home> { HomeScreen(navController = navController) }
//        HomeScreen(navController = navController)
    }
}

@Preview
@Composable
fun CompanionNavigationPreview() {
    val navController = rememberNavController()

    HomeScreen(navController = navController)
}
