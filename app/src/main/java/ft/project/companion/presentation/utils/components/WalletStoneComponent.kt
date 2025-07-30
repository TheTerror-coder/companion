package ft.project.companion.presentation.utils.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ft.project.companion.presentation.utils.components.companionvectorspack.MyIconPack
import ft.project.companion.presentation.utils.components.companionvectorspack.myiconpack.Walletstone

@Composable
fun WalletStoneComponent(
    walletContent: String,
    modifier: Modifier = Modifier,
    walletStoneColor: Color = MaterialTheme.colorScheme.primary,
) {
    StoneComponent(
        stone = MyIconPack.Walletstone,
        stoneColor = walletStoneColor,
        itemName = "wallet",
        itemContent = walletContent,
        itemUnit = "₳",
        itemIcon = null,
        modifier = modifier,
        )
}

@Preview
@Composable
fun WalletPreview() {
    WalletStoneComponent("100000")
}