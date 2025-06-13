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

public val MyIconPack.Walletstone: ImageVector
    get() {
        if (_walletstone != null) {
            return _walletstone!!
        }
        _walletstone = Builder(name = "Walletstone", defaultWidth = 137.0.dp, defaultHeight =
                122.0.dp, viewportWidth = 137.0f, viewportHeight = 122.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(33.54f, 11.99f)
                curveTo(40.55f, 4.35f, 50.44f, 0.0f, 60.81f, 0.0f)
                horizontalLineTo(79.78f)
                curveTo(91.13f, 0.0f, 101.85f, 5.21f, 108.86f, 14.13f)
                lineTo(128.57f, 39.2f)
                curveTo(139.36f, 52.92f, 139.09f, 72.32f, 127.93f, 85.74f)
                lineTo(108.85f, 108.67f)
                curveTo(101.82f, 117.11f, 91.4f, 122.0f, 80.41f, 122.0f)
                horizontalLineTo(60.19f)
                curveTo(50.18f, 122.0f, 40.61f, 117.95f, 33.64f, 110.77f)
                lineTo(11.32f, 87.78f)
                curveTo(-2.33f, 73.72f, -2.65f, 51.45f, 10.6f, 37.0f)
                lineTo(33.54f, 11.99f)
                close()
            }
        }
        .build()
        return _walletstone!!
    }

private var _walletstone: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Walletstone, contentDescription = "")
    }
}
