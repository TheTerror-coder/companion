package ft.project.`companion`.presentation.utils.components.companionvectorspack.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ft.project.`companion`.presentation.utils.components.companionvectorspack.MyIconPack
import kotlin.Unit

public val MyIconPack.Locationstone: ImageVector
    get() {
        if (_locationstone != null) {
            return _locationstone!!
        }
        _locationstone = Builder(name = "Locationstone", defaultWidth = 178.0.dp, defaultHeight =
                122.0.dp, viewportWidth = 178.0f, viewportHeight = 122.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(26.85f, 21.72f)
                curveTo(38.16f, 11.01f, 53.06f, 5.35f, 68.48f, 3.15f)
                lineTo(77.74f, 1.83f)
                curveTo(86.28f, 0.62f, 94.94f, 0.54f, 103.5f, 1.62f)
                lineTo(112.91f, 2.8f)
                curveTo(132.13f, 5.21f, 150.79f, 12.91f, 162.45f, 28.39f)
                curveTo(173.44f, 42.96f, 183.25f, 62.19f, 174.75f, 77.56f)
                curveTo(166.23f, 92.96f, 120.79f, 112.48f, 103.64f, 119.41f)
                curveTo(99.3f, 121.17f, 94.68f, 122.0f, 90.01f, 122.0f)
                verticalLineTo(122.0f)
                curveTo(85.46f, 122.0f, 80.96f, 121.21f, 76.73f, 119.54f)
                curveTo(59.7f, 112.85f, 13.91f, 93.72f, 3.47f, 77.56f)
                curveTo(-7.9f, 59.96f, 10.97f, 36.74f, 26.85f, 21.72f)
                close()
            }
        }
        .build()
        return _locationstone!!
    }

private var _locationstone: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Locationstone, contentDescription = "")
    }
}
