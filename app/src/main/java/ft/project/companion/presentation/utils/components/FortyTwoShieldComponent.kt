package ft.project.companion.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ft.project.companion.presentation.navigation.Home
import ft.project.companion.presentation.utils.components.companionvectorspack.MyIconPack
import ft.project.companion.presentation.utils.components.companionvectorspack.myiconpack.Fortytwo
import ft.project.companion.presentation.utils.components.companionvectorspack.myiconpack.Shield
import ft.project.companion.presentation.viewmodels.FortyTwoShieldViewModel

@Composable
fun FortyTwoShieldComponent(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: FortyTwoShieldViewModel,
    fortyTwoShieldColor: Color = MaterialTheme.colorScheme.secondary,
    pressedFortyTwoShieldColor: Color = MaterialTheme.colorScheme.onPrimary,
){
    val pressed = viewModel.fortyTwoShieldIsPressed.value
    val navigateToHome = viewModel.navigateToHome.value

    var currentFortyTwoShieldColor = remember (pressed, pressedFortyTwoShieldColor, fortyTwoShieldColor) {
        if (pressed) pressedFortyTwoShieldColor else fortyTwoShieldColor
    }

    if (navigateToHome) {
        navController.navigate(Home)
        viewModel.navigatedToHome()
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
                            viewModel.setFortyTwoShieldPressed(true)
                            tryAwaitRelease()
                            viewModel.setFortyTwoShieldPressed(false)
                        },
                        onTap = {
                            viewModel.onFortyTwoShieldTap()
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