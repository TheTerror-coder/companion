package ft.project.companion.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ft.project.companion.presentation.utils.components.companionvectorspack.MyIconPack
import ft.project.companion.presentation.utils.components.companionvectorspack.myiconpack.Walletstone

@Composable
fun WalletStoneComponent(
    walletContent: String,
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    walletStoneColor: Color = MaterialTheme.colorScheme.primary,
    fontSize: TextUnit? = null
) {
    StoneComponent(
        modifier = modifier,
        stone = MyIconPack.Walletstone,
        stoneColor = walletStoneColor,
        itemName = "wallet",
        itemContent = walletContent,
        itemUnit = "₳",
        itemIcon = null,
        fontSize = fontSize
    )
}

@Preview
@Composable
fun WalletPreview() {
    Surface(
        modifier = Modifier
            .width(600.dp),
        color = Color.Gray
    ) {
        WalletStoneComponent(
            walletContent = "10",
            fontSize = 50.sp
        )
    }
}