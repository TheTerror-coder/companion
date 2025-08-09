package ft.project.companion.presentation.utils.extensions

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import ft.project.companion.presentation.utils.ui.MeasureDefaults

/**
 * computes a font size value that is a fraction of the given Unit.Dp value
 * then returns it
 */
fun Dp.computeFontSize(fraction: Float = MeasureDefaults.FRACTION_1F): TextUnit{
    return ((this.value * MeasureDefaults.FRACTION_5F) * fraction).sp
}