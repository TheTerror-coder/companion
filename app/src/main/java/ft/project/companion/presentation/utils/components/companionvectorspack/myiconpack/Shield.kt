package ft.project.`companion`.presentation.utils.components.companionvectorspack.myiconpack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ft.project.`companion`.presentation.utils.components.companionvectorspack.MyIconPack
import kotlin.Unit

public val MyIconPack.Shield: ImageVector
    get() {
        if (_shield != null) {
            return _shield!!
        }
        _shield = Builder(name = "Shield", defaultWidth = 648.0.dp, defaultHeight = 798.0.dp,
                viewportWidth = 648.0f, viewportHeight = 798.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 44.4103f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(625.0f, 436.89f)
                curveTo(625.0f, 625.02f, 493.31f, 719.08f, 336.79f, 773.64f)
                curveTo(328.6f, 776.41f, 319.69f, 776.28f, 311.58f, 773.26f)
                curveTo(154.69f, 719.08f, 23.0f, 625.02f, 23.0f, 436.89f)
                verticalLineTo(173.52f)
                curveTo(23.0f, 163.54f, 26.96f, 153.97f, 34.02f, 146.91f)
                curveTo(41.08f, 139.86f, 50.65f, 135.89f, 60.63f, 135.89f)
                curveTo(135.88f, 135.89f, 229.94f, 90.74f, 295.4f, 33.55f)
                curveTo(303.38f, 26.74f, 313.52f, 23.0f, 324.0f, 23.0f)
                curveTo(334.48f, 23.0f, 344.62f, 26.74f, 352.6f, 33.55f)
                curveTo(418.44f, 91.12f, 512.13f, 135.89f, 587.38f, 135.89f)
                curveTo(597.35f, 135.89f, 606.92f, 139.86f, 613.98f, 146.91f)
                curveTo(621.04f, 153.97f, 625.0f, 163.54f, 625.0f, 173.52f)
                verticalLineTo(436.89f)
                close()
            }
        }
        .build()
        return _shield!!
    }

private var _shield: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Shield, contentDescription = "")
    }
}
