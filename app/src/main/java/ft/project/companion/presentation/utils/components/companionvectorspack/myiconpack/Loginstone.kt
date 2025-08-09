package ft.project.`companion`.presentation.utils.components.companionvectorspack.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
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

public val MyIconPack.Loginstone: ImageVector
    get() {
        if (_loginstone != null) {
            return _loginstone!!
        }
        _loginstone = Builder(name = "Loginstone", defaultWidth = 1131.0.dp, defaultHeight =
                1002.0.dp, viewportWidth = 1131.0f, viewportHeight = 1002.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(1130.8f, 642.3f)
                curveTo(1130.8f, 892.5f, 851.2f, 1001.5f, 573.5f, 1001.5f)
                curveTo(295.9f, 1001.5f, 4.4f, 981.5f, 4.4f, 726.5f)
                curveTo(4.4f, 722.0f, 20.3f, 643.0f, 24.4f, 624.1f)
                curveTo(37.3f, 563.9f, 62.9f, 443.9f, 51.9f, 350.0f)
                curveTo(43.9f, 282.2f, 30.5f, 205.7f, 19.9f, 145.2f)
                curveTo(9.7f, 86.7f, -8.3f, 72.5f, 4.4f, 36.5f)
                curveTo(17.1f, 0.5f, 183.9f, -41.0f, 215.7f, 85.5f)
                curveTo(225.2f, 123.0f, 223.6f, 172.8f, 204.3f, 237.0f)
                curveTo(201.0f, 247.9f, 198.6f, 258.4f, 197.0f, 268.5f)
                curveTo(195.3f, 279.2f, 194.5f, 289.3f, 194.5f, 299.0f)
                curveTo(194.7f, 322.0f, 199.6f, 342.1f, 208.5f, 359.5f)
                curveTo(243.0f, 427.4f, 336.6f, 454.0f, 430.8f, 452.1f)
                curveTo(510.0f, 450.5f, 589.5f, 428.9f, 634.8f, 395.0f)
                curveTo(649.1f, 384.3f, 666.3f, 359.5f, 666.3f, 359.5f)
                curveTo(675.8f, 340.3f, 690.5f, 324.5f, 708.8f, 311.9f)
                curveTo(728.8f, 298.2f, 753.2f, 288.2f, 779.8f, 281.8f)
                curveTo(888.4f, 255.7f, 1034.8f, 289.1f, 1078.8f, 368.5f)
                curveTo(1121.0f, 444.5f, 1130.8f, 543.6f, 1130.8f, 642.3f)
                close()
            }
        }
        .build()
        return _loginstone!!
    }

private var _loginstone: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Loginstone, contentDescription = "")
    }
}
