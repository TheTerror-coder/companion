package ft.project.companion.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ft.project.companion.presentation.utils.components.companionvectorspack.MyIconPack
import ft.project.companion.presentation.utils.components.companionvectorspack.myiconpack.Loginstone

@Composable
fun LoginStoneComponent(
    login: String,
    modifier: Modifier = Modifier,
    stoneColor: Color = MaterialTheme.colorScheme.primary
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            imageVector = MyIconPack.Loginstone,
            contentDescription = "",
            colorFilter = ColorFilter.tint(stoneColor)
        )
        SingleLineTextComponent(
            text = login,
            color = MaterialTheme.colorScheme.surface,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 80.sp
        )
    }
}

@Preview
@Composable
fun LoginStoneComponentPreview() {
    LoginStoneComponent(login = "jfaye")
}