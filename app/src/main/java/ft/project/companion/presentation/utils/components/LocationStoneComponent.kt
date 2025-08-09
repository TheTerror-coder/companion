package ft.project.companion.presentation.utils.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ft.project.companion.presentation.utils.components.companionvectorspack.MyIconPack
import ft.project.companion.presentation.utils.components.companionvectorspack.myiconpack.Locationstone

@Composable
fun LocationStoneComponent(
    locationContent: String,
    modifier: Modifier = Modifier,
    locationStoneColor: Color = MaterialTheme.colorScheme.primary,
    fontSize: TextUnit? = null
) {
    StoneComponent(
        modifier = modifier,
        stone = MyIconPack.Locationstone,
        stoneColor = locationStoneColor,
        itemName = "location",
        itemContent = locationContent,
        itemUnit = null,
        itemIcon = Icons.Filled.LocationOn,
        fontSize = fontSize
    )
}

@Preview()
@Composable
fun locationPreview() {
    Surface(
        modifier = Modifier
            .width(600.dp),
        color = Color.Gray
    ) {
        LocationStoneComponent(
            modifier = Modifier
                .fillMaxWidth(),
            locationContent = "Lyon",
            fontSize = 50.sp
        )

    }
}