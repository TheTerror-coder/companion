package ft.project.companion.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ft.project.companion.presentation.utils.components.FortyTwoShieldComponent
import ft.project.companion.presentation.viewmodels.AuthenticationState
import ft.project.companion.presentation.viewmodels.FortyTwoShieldViewModel
import kotlinx.coroutines.flow.StateFlow

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
