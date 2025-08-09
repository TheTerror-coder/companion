package ft.project.companion.presentation.utils.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import ft.project.companion.presentation.utils.extensions.computeBodyFontSize
import ft.project.companion.presentation.utils.extensions.computeHeaderFontSize
import ft.project.companion.presentation.utils.ui.MeasureDefaults

@Composable
fun UserInfoFieldModelComponent(
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    infoType: String,
    infoContent: String,
    infoIcon: ImageVector,
    fontSize: TextUnit? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {

        val headerFontSize: TextUnit = fontSize.computeHeaderFontSize()
        val bodyFontSize: TextUnit = fontSize.computeBodyFontSize()

        //information type(header)
        Box(
            modifier = Modifier
                .fillMaxHeight(MeasureDefaults.FRACTION_5F),
            contentAlignment = Alignment.BottomStart
        ) {
            SingleLineTextComponent(
                text = infoType,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = headerFontSize,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            //icon
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.08f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.CenterStart,
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize(),
                    imageVector = infoIcon,
                    contentDescription = null
                )
            }
            //information content
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterEnd,
            ) {
                SingleLineTextComponent(
                    text = infoContent,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = bodyFontSize,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun UserInfoFieldModelComponentPreview() {
    Surface(
        modifier = Modifier
            .width(400.dp)
            .height(200.dp)
    ) {
        UserInfoFieldModelComponent(
            infoType = "Email",
            infoContent = "johnnydoe45@gmail.com",
            infoIcon = Icons.Filled.Email,
        )

    }
}