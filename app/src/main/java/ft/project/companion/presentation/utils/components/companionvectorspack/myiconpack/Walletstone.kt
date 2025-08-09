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
        _walletstone = Builder(name = "Walletstone", defaultWidth = 588.0.dp, defaultHeight =
                480.0.dp, viewportWidth = 588.0f, viewportHeight = 480.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(183.35f, 20.83f)
                curveTo(195.53f, 7.56f, 212.71f, 0.0f, 230.73f, 0.0f)
                horizontalLineTo(380.66f)
                curveTo(400.38f, 0.0f, 419.01f, 9.05f, 431.2f, 24.55f)
                lineTo(573.84f, 205.91f)
                curveTo(592.59f, 229.76f, 592.13f, 263.48f, 572.71f, 286.8f)
                lineTo(431.17f, 456.84f)
                curveTo(418.96f, 471.51f, 400.85f, 480.0f, 381.75f, 480.0f)
                horizontalLineTo(229.65f)
                curveTo(212.27f, 480.0f, 195.63f, 472.96f, 183.52f, 460.5f)
                lineTo(18.29f, 290.35f)
                curveTo(-5.45f, 265.91f, -6.0f, 227.19f, 17.04f, 202.09f)
                lineTo(183.35f, 20.83f)
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
