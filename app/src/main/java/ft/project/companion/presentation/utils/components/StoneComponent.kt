package ft.project.companion.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun StoneComponent(
    stone: ImageVector,
    stoneColor: Color = MaterialTheme.colorScheme.primary,
    itemName: String,
    itemContent: String,
    itemUnit: String?,
    itemIcon: ImageVector?,
    itemIconColor: Color = MaterialTheme.colorScheme.surface,
    modifier: Modifier,
) {
    Box(
        modifier =  modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = stone,
            contentDescription = null,
            colorFilter = ColorFilter.tint(stoneColor),
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Box(
                contentAlignment = Alignment.Center
            ){
                SingleLineTextComponent(
                    text = itemName,
                    color = MaterialTheme.colorScheme.surface,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                if (itemIcon != null) {
                    Icon(
                        imageVector = itemIcon,
                        contentDescription = null,
                        tint = itemIconColor,
                        )
                }

                SingleLineTextComponent(
                    text = itemContent,
                    color = MaterialTheme.colorScheme.surface,
                    style = MaterialTheme.typography.bodyLarge
                )

                if (itemUnit != null) {
                    SingleLineTextComponent(
                        text = itemUnit.toString(),
                        color = MaterialTheme.colorScheme.surface,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}