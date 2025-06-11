package ft.project.companion.presentation.composables.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ft.project.companion.presentation.utils.components.FortyTwoShieldComponent
import ft.project.companion.presentation.viewmodels.FortyTwoShieldViewModel

@Composable
fun FortyTwoShieldScreen(modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val viewModel: FortyTwoShieldViewModel = viewModel()

        FortyTwoShieldComponent(viewModel = viewModel)
    }
}
