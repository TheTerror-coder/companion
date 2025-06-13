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
        _loginstone = Builder(name = "Loginstone", defaultWidth = 350.0.dp, defaultHeight =
                283.0.dp, viewportWidth = 350.0f, viewportHeight = 283.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(175.0f, 283.0f)
                curveTo(271.65f, 283.0f, 350.0f, 212.68f, 350.0f, 125.93f)
                curveTo(350.0f, 39.18f, 334.09f, -46.1f, 175.0f, 28.7f)
                curveTo(68.18f, 56.48f, 0.0f, 39.18f, 0.0f, 125.93f)
                curveTo(0.0f, 212.68f, 78.35f, 283.0f, 175.0f, 283.0f)
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
