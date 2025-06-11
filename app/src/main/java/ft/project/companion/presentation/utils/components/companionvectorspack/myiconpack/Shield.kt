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
        _shield = Builder(name = "Shield", defaultWidth = 299.0.dp, defaultHeight = 369.0.dp,
                viewportWidth = 299.0f, viewportHeight = 369.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 20.48f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(288.61f, 201.87f)
                curveTo(288.61f, 288.62f, 227.89f, 332.0f, 155.71f, 357.16f)
                curveTo(151.93f, 358.44f, 147.82f, 358.38f, 144.08f, 356.99f)
                curveTo(71.73f, 332.0f, 11.0f, 288.62f, 11.0f, 201.87f)
                verticalLineTo(80.41f)
                curveTo(11.0f, 75.81f, 12.83f, 71.4f, 16.08f, 68.14f)
                curveTo(19.34f, 64.89f, 23.75f, 63.06f, 28.35f, 63.06f)
                curveTo(63.05f, 63.06f, 106.43f, 42.24f, 136.62f, 15.87f)
                curveTo(140.3f, 12.73f, 144.97f, 11.0f, 149.81f, 11.0f)
                curveTo(154.64f, 11.0f, 159.32f, 12.73f, 162.99f, 15.87f)
                curveTo(193.36f, 42.41f, 236.56f, 63.06f, 271.26f, 63.06f)
                curveTo(275.87f, 63.06f, 280.28f, 64.89f, 283.53f, 68.14f)
                curveTo(286.79f, 71.4f, 288.61f, 75.81f, 288.61f, 80.41f)
                verticalLineTo(201.87f)
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
