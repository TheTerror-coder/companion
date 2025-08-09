package ft.project.companion.presentation.authentication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
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
                ConstraintLayout(
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    val fortyTwoShieldComponentRef = createRef()

                    FortyTwoShieldComponent(
                        modifier = Modifier
                            .constrainAs(fortyTwoShieldComponentRef){
                                linkTo(
                                    start = parent.start,
                                    top = parent.top,
                                    end = parent.end,
                                    bottom = parent.bottom
                                )
                            },
                        authUiState = authUiState,
                        onAuthUiAction = onAuthUiAction,
                        onFortyTwoShieldClick = onFortyTwoShieldClick,
                        onHomeNavigate = onHomeNavigate,
                    )
                }
            }
    }
}

@Preview
@Composable
private fun Preview(){
    AuthenticationScreen(
        authUiState = AuthenticationState(),
        onAuthUiAction = {},
        onHomeNavigate = {},
        onFortyTwoShieldClick = {}
    )
}
