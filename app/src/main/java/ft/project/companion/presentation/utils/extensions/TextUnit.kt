package ft.project.companion.presentation.utils.extensions

import androidx.compose.ui.unit.TextUnit
import ft.project.companion.presentation.utils.ui.MeasureDefaults

/**
 * computes a the needed header font size: a fraction of the given TextUnit
 */
fun TextUnit?.computeHeaderFontSize(): TextUnit{
    return this ?: TextUnit.Unspecified
}

/**
 * computes a the needed body font size: a fraction of the given TextUnit
 */
fun TextUnit?.computeBodyFontSize(fraction: Float = MeasureDefaults.FRACTION_8F): TextUnit{
    return if (this != null) {
            this * fraction
    } else {
        TextUnit.Unspecified
    }
}