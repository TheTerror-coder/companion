package ft.project.companion.presentation.utils.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ft.project.companion.presentation.utils.components.companionvectorspack.MyIconPack
import ft.project.companion.presentation.utils.components.companionvectorspack.myiconpack.Locationstone

@Composable
fun LocationStoneComponent(
    locationContent: String,
    modifier: Modifier = Modifier,
    locationStoneColor: Color = MaterialTheme.colorScheme.primary,
) {
    StoneComponent(
        stone = MyIconPack.Locationstone,
        stoneColor = locationStoneColor,
        itemName = "location",
        itemContent = locationContent,
        itemUnit = null,
        itemIcon = Icons.Filled.LocationOn,
        modifier = modifier,
    )
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun locationPreview() {
    LocationStoneComponent("Lyon")
}