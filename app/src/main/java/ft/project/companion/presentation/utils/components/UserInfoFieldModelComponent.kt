package ft.project.companion.presentation.utils.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UserInfoFieldModelComponent(
    infoType: String,
    infoContent: String,
    infoIcon: ImageVector,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        //information type
        SingleLineTextComponent(
            text = infoType,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            //icon
            Box(
                modifier = modifier.fillMaxWidth(0.12f),
                contentAlignment = Alignment.CenterStart,
            ) {
                Icon(imageVector = infoIcon, contentDescription = null)
            }
            //information content
            Box(
                modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd,
            ) {
                SingleLineTextComponent(
                    text = infoContent,
                    color = MaterialTheme.colorScheme.onSurface,
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
    UserInfoFieldModelComponent(
        infoType = "Email",
        infoContent = "johnnydoe12345@gmail.com",
        infoIcon = Icons.Filled.Email,
    )
}