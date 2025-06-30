package ft.project.companion.presentation.utils.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ft.project.companion.TAG
import ft.project.companion.presentation.composables.AuthenticationUiAction
import ft.project.companion.presentation.navigation.Home
import ft.project.companion.presentation.utils.components.companionvectorspack.MyIconPack
import ft.project.companion.presentation.utils.components.companionvectorspack.myiconpack.Fortytwo
import ft.project.companion.presentation.utils.components.companionvectorspack.myiconpack.Shield
import ft.project.companion.presentation.viewmodels.AuthenticationState
import ft.project.companion.presentation.viewmodels.FortyTwoShieldViewModel

@Composable
fun FortyTwoShieldComponent(
    navController: NavController,
    modifier: Modifier = Modifier,
    authUiState: AuthenticationState,
    onAuthUiAction: (AuthenticationUiAction) -> Unit,
//    viewModel: FortyTwoShieldViewModel,
    fortyTwoShieldColor: Color = MaterialTheme.colorScheme.secondary,
    pressedFortyTwoShieldColor: Color = MaterialTheme.colorScheme.onPrimary,
    onFortyTwoShieldClick: () -> Unit,
){
    val pressed = authUiState.fortyTwoShieldIsPressed
    val navigateToHome = authUiState.navigateToHome

    var currentFortyTwoShieldColor = remember (pressed, pressedFortyTwoShieldColor, fortyTwoShieldColor) {
        if (pressed) pressedFortyTwoShieldColor else fortyTwoShieldColor
    }

    if (navigateToHome) {
        LaunchedEffect(Unit) {
            navController.navigate(Home)
            onAuthUiAction(AuthenticationUiAction.navigatedToHome)
        }
    }

    Box(
        modifier = modifier
            .padding(10.dp)
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = MyIconPack.Shield,
            contentDescription = "shield icon",
            colorFilter = ColorFilter.tint(currentFortyTwoShieldColor),
            modifier = modifier
                .pointerInput(Unit){
                    detectTapGestures(
                        onPress = {
                            onAuthUiAction(AuthenticationUiAction.fortyTwoShieldPressed)
                            tryAwaitRelease()
                            onAuthUiAction(AuthenticationUiAction.fortyTwoShieldUnpressed)
                        },
                        onTap = {
                            if (authUiState.authState.isAuthorized){
                                onAuthUiAction(AuthenticationUiAction.onFortyTwoShieldTap)
                            }
                            else{
                                onFortyTwoShieldClick()
                                Log.d(TAG, "******************FortyTwoShieldComponent: step over onFortyTwoShieldClick lamda")
                            }
                        }
                    )
                }
        )
        Image(
            imageVector = MyIconPack.Fortytwo,
            contentDescription = "42 icon",
            colorFilter = ColorFilter.tint(currentFortyTwoShieldColor)
        )
    }
}

//@Preview
//@Composable
//private fun Preview() {
//    Box(
//        modifier = Modifier
//    ) {
//        val viewModel: FortyTwoShieldViewModel = viewModel()
//
//        FortyTwoShieldComponent(viewModel = viewModel)
//    }
//}