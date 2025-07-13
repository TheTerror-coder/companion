package ft.project.companion

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ft.project.companion.presentation.authentication.AuthenticationScreen
import ft.project.companion.presentation.authentication.AuthenticationState
import ft.project.companion.presentation.authentication.AuthenticationUiAction
import ft.project.companion.presentation.home.HomeScreen
import ft.project.companion.presentation.home.HomeViewModel
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
        composable<AuthenticationRoute> {
            AuthenticationScreen(
                authUiState = authUiState,
                onAuthUiAction = onAuthUiAction,
                onFortyTwoShieldClick = onFortyTwoShieldClick,
                onHomeNavigate = {
                    navController.navigate(route = HomeRoute)
                }
            )
        }
        composable<HomeRoute> {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen()
        }
    }
}

