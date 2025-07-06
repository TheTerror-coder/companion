package ft.project.companion.presentation.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ft.project.companion.presentation.utils.components.FortyTwoShieldComponent

@Composable
fun AuthenticationScreen(
    authUiState: AuthenticationState,
    onAuthUiAction: (AuthenticationUiAction) -> Unit,
    modifier: Modifier = Modifier,
    onFortyTwoShieldClick: () -> Unit,
    onHomeNavigate: () -> Unit,
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FortyTwoShieldComponent(
            authUiState = authUiState,
            onAuthUiAction = onAuthUiAction,
            onFortyTwoShieldClick = onFortyTwoShieldClick,
            onHomeNavigate = onHomeNavigate,
        )
    }
}
