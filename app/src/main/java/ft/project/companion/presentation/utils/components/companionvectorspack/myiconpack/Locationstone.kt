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
        _locationstone = Builder(name = "Locationstone", defaultWidth = 630.0.dp, defaultHeight =
                480.0.dp, viewportWidth = 630.0f, viewportHeight = 480.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(131.85f, 45.43f)
                curveTo(145.62f, 31.97f, 162.72f, 22.65f, 181.13f, 16.99f)
                lineTo(216.07f, 6.23f)
                curveTo(229.5f, 2.1f, 243.47f, 0.0f, 257.52f, 0.0f)
                horizontalLineTo(318.86f)
                horizontalLineTo(398.73f)
                curveTo(411.99f, 0.0f, 425.19f, 1.87f, 437.93f, 5.56f)
                lineTo(471.57f, 15.31f)
                curveTo(494.81f, 22.04f, 516.01f, 34.52f, 531.46f, 53.14f)
                curveTo(573.22f, 103.44f, 651.31f, 213.4f, 624.53f, 305.14f)
                curveTo(613.51f, 342.92f, 536.52f, 393.83f, 493.83f, 431.33f)
                curveTo(487.2f, 437.15f, 462.2f, 444.18f, 453.82f, 446.97f)
                verticalLineTo(446.97f)
                curveTo(425.04f, 456.54f, 391.08f, 480.0f, 360.75f, 480.0f)
                horizontalLineTo(284.86f)
                curveTo(254.61f, 480.0f, 225.7f, 464.43f, 200.59f, 447.55f)
                curveTo(187.95f, 439.05f, 171.44f, 429.91f, 150.24f, 421.47f)
                curveTo(136.48f, 415.99f, 121.36f, 414.65f, 107.25f, 410.14f)
                curveTo(60.55f, 395.23f, 8.75f, 339.11f, 2.34f, 305.14f)
                curveTo(-16.76f, 203.89f, 85.88f, 90.38f, 131.85f, 45.43f)
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
