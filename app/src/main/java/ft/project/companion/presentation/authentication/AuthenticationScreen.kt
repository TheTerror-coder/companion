package ft.project.companion.presentation.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ft.project.companion.presentation.utils.components.FortyTwoShieldComponent
import ft.project.companion.utils.ScreenSpaceConfig

@Composable
fun AuthenticationScreen(
    authUiState: AuthenticationState,
    onAuthUiAction: (AuthenticationUiAction) -> Unit,
    modifier: Modifier = Modifier,
    onFortyTwoShieldClick: () -> Unit,
    onHomeNavigate: () -> Unit,
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val screenSpaceConfig = ScreenSpaceConfig.fromWindowSizeClass(windowSizeClass = windowSizeClass)

    when {
        screenSpaceConfig == ScreenSpaceConfig.MOBILE_PORTRAIT ||
        screenSpaceConfig == ScreenSpaceConfig.MOBILE_LANDSCAPE ||
        screenSpaceConfig == ScreenSpaceConfig.TABLET_PORTRAIT ||
        screenSpaceConfig == ScreenSpaceConfig.TABLET_LANDSCAPE ||
        screenSpaceConfig == ScreenSpaceConfig.DESKTOP
            -> {
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
    }
}
