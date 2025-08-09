package ft.project.companion.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import ft.project.companion.presentation.utils.components.companionvectorspack.MyIconPack
import ft.project.companion.presentation.utils.components.companionvectorspack.myiconpack.Walletstone
import ft.project.companion.presentation.utils.extensions.computeBodyFontSize
import ft.project.companion.presentation.utils.extensions.computeHeaderFontSize
import ft.project.companion.presentation.utils.ui.MeasureDefaults

@Composable
fun StoneComponent(
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    stone: ImageVector,
    stoneColor: Color = MaterialTheme.colorScheme.primary,
    itemName: String,
    itemContent: String,
    itemUnit: String?,
    itemIcon: ImageVector?,
    itemIconColor: Color = MaterialTheme.colorScheme.surface,
    fontSize: TextUnit? = null
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        /**
         * draws the stone
         */
        Image(
            imageVector = stone,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(MeasureDefaults.FRACTION_8F),
            colorFilter = ColorFilter.tint(stoneColor),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(MeasureDefaults.FRACTION_5F),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            val headerFontSize: TextUnit = fontSize.computeHeaderFontSize()
            val bodyFontSize: TextUnit = fontSize.computeBodyFontSize()

            SingleLineTextComponent(
                text = itemName,
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface,
                fontSize = headerFontSize,
//                autoSize = TextAutoSize.StepBased(stepSize = MeasureDefaults.TEXT_UNIT_5SP),
                style = MaterialTheme.typography.titleMedium
            )

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                val rowHeight = maxWidth * MeasureDefaults.FRACTION_3F
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(rowHeight),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    if (itemIcon != null) {
                        Icon(
                            imageVector = itemIcon,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxHeight(),
                            tint = itemIconColor,
                        )
                    }

                    val maxWalletDigits = 7
                    SingleLineTextComponent(
                        text = itemContent.let {
                            if (it.length <= maxWalletDigits) { it }
                            it.take(maxWalletDigits)
                        },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .fillMaxWidth(MeasureDefaults.FRACTION_7F),
                        color = MaterialTheme.colorScheme.surface,
                        fontSize = bodyFontSize,
//                        autoSize = TextAutoSize.StepBased(stepSize = MeasureDefaults.TEXT_UNIT_5SP),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    if (itemUnit != null) {
                        SingleLineTextComponent(
                            text = itemUnit,
                            modifier = Modifier
                                .fillMaxHeight(),
                            color = MaterialTheme.colorScheme.surface,
                            fontSize = bodyFontSize,
//                            autoSize = TextAutoSize.StepBased(stepSize = MeasureDefaults.TEXT_UNIT_5SP),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview(){
    Surface(
        modifier = Modifier
            .width(400.dp),
        color = (Color.Gray),
    ){
        StoneComponent(
//            modifier = Modifier,
            stone = MyIconPack.Walletstone,
            itemName = "Mocking",
            itemContent = "10000",
            itemUnit = "₳",
            itemIcon = null//Icons.Filled.LocationOn,
        )

    }
}