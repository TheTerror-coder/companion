package ft.project.companion.presentation.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ft.project.companion.presentation.utils.components.FortyTwoShieldComponent

@Composable
fun AuthenticationScreen(
    navController: NavController,
    authUiState: AuthenticationState,
    onAuthUiAction: (AuthenticationUiAction) -> Unit,
    modifier: Modifier = Modifier,
    onFortyTwoShieldClick: () -> Unit,
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        val viewModel: FortyTwoShieldViewModel = viewModel()

        FortyTwoShieldComponent(
            navController = navController,
            authUiState = authUiState,
            onAuthUiAction = onAuthUiAction,
            onFortyTwoShieldClick = onFortyTwoShieldClick
        )
    }
}
