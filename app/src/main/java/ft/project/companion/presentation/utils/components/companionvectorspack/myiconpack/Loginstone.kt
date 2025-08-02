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
        _loginstone = Builder(name = "Loginstone", defaultWidth = 369.0.dp, defaultHeight =
                267.0.dp, viewportWidth = 369.0f, viewportHeight = 267.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(175.0f, 267.0f)
                curveTo(271.65f, 267.0f, 369.0f, 229.25f, 369.0f, 142.5f)
                curveTo(369.0f, 55.75f, 347.0f, -32.04f, 175.0f, 12.7f)
                curveTo(68.18f, 40.48f, 0.0f, 23.18f, 0.0f, 109.93f)
                curveTo(0.0f, 196.68f, 78.35f, 267.0f, 175.0f, 267.0f)
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
