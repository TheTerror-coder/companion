package ft.project.companion.presentation.utils.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import ft.project.companion.TAG
import ft.project.companion.presentation.authentication.AuthenticationUiAction
import ft.project.companion.presentation.utils.components.companionvectorspack.MyIconPack
import ft.project.companion.presentation.utils.components.companionvectorspack.myiconpack.Fortytwo
import ft.project.companion.presentation.utils.components.companionvectorspack.myiconpack.Shield
import ft.project.companion.presentation.authentication.AuthenticationState
import ft.project.companion.presentation.utils.ui.MeasureDefaults

@Composable
fun FortyTwoShieldComponent(
    modifier: Modifier = Modifier,
    authUiState: AuthenticationState,
    onAuthUiAction: (AuthenticationUiAction) -> Unit,
    fortyTwoShieldColor: Color = MaterialTheme.colorScheme.outline,
    pressedFortyTwoShieldColor: Color = MaterialTheme.colorScheme.primary,
    onFortyTwoShieldClick: () -> Unit,
    onHomeNavigate: () -> Unit,
){
    val pressed = authUiState.fortyTwoShieldIsPressed
    val authorized = authUiState.isAuthorized

    var currentFortyTwoShieldColor = remember(
        pressed,
        pressedFortyTwoShieldColor,
        fortyTwoShieldColor
    ) {
        if (pressed) pressedFortyTwoShieldColor else fortyTwoShieldColor
    }

    if (authorized) {
        LaunchedEffect(Unit) {
            onHomeNavigate()
        }
    }

    Box(
        modifier = modifier
            .padding(10.dp)
            .widthIn(100.dp, 500.dp)
            .fillMaxWidth(MeasureDefaults.FRACTION_7F),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = MyIconPack.Shield,
            contentDescription = "shield icon",
            colorFilter = ColorFilter.tint(currentFortyTwoShieldColor),
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit){
                    detectTapGestures(
                        onPress = {
                            onAuthUiAction(AuthenticationUiAction.fortyTwoShieldPressed)
                            tryAwaitRelease()
                            onAuthUiAction(AuthenticationUiAction.fortyTwoShieldUnpressed)
                        },
                        onTap = {
                            if (authUiState.isAuthorized){
                                onHomeNavigate()
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
            modifier = Modifier
                .fillMaxWidth(MeasureDefaults.FRACTION_7F),
            colorFilter = ColorFilter.tint(currentFortyTwoShieldColor)
        )
    }
}

@Preview
@Composable
fun previewFortyTwoShieldComponent() {
    Surface(
        modifier = Modifier
            .width(300.dp)
    ) {
        FortyTwoShieldComponent(
            authUiState = AuthenticationState(),
            onAuthUiAction = { },
            onFortyTwoShieldClick = {},
            onHomeNavigate = {}
        )

    }
}