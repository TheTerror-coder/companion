package ft.project.companion.utils

import androidx.window.core.layout.WindowSizeClass

private const val WIDTH_DP_MEDIUM_LOWER_BOUND: Int = 600
private const val WIDTH_DP_EXPANDED_LOWER_BOUND: Int = 840
private const val WIDTH_DP_LARGE_LOWER_BOUND: Int = 1200
private const val WIDTH_DP_EXTRA_LARGE_LOWER_BOUND: Int = 1600

private const val HEIGHT_DP_MEDIUM_LOWER_BOUND: Int = 480
private const val HEIGHT_DP_EXPANDED_LOWER_BOUND: Int = 900

enum class ScreenSpaceConfig {

    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE,
    TABLET_PORTRAIT,
    TABLET_LANDSCAPE,
    DESKTOP;

    companion object{
        fun fromWindowSizeClass(windowSizeClass: WindowSizeClass): ScreenSpaceConfig {

            return when {
                windowSizeClass.isWidthAtLeastBreakpoint(
                    WIDTH_DP_LARGE_LOWER_BOUND
                ) -> DESKTOP
                windowSizeClass.isWidthAtLeastBreakpoint(
                    WIDTH_DP_EXPANDED_LOWER_BOUND
                ) && windowSizeClass.isHeightAtLeastBreakpoint(
                    HEIGHT_DP_EXPANDED_LOWER_BOUND
                ) -> TABLET_PORTRAIT
                windowSizeClass.isWidthAtLeastBreakpoint(
                    WIDTH_DP_EXPANDED_LOWER_BOUND
                ) && windowSizeClass.isHeightAtLeastBreakpoint(
                    HEIGHT_DP_MEDIUM_LOWER_BOUND
                ) -> TABLET_LANDSCAPE
                windowSizeClass.isWidthAtLeastBreakpoint(
                    WIDTH_DP_MEDIUM_LOWER_BOUND
                ) && windowSizeClass.isHeightAtLeastBreakpoint(
                    HEIGHT_DP_MEDIUM_LOWER_BOUND
                ) -> TABLET_PORTRAIT
                windowSizeClass.isWidthAtLeastBreakpoint(
                    WIDTH_DP_MEDIUM_LOWER_BOUND
                ) && !windowSizeClass.isHeightAtLeastBreakpoint(
                    HEIGHT_DP_MEDIUM_LOWER_BOUND
                ) -> MOBILE_LANDSCAPE
                !windowSizeClass.isWidthAtLeastBreakpoint(
                    WIDTH_DP_MEDIUM_LOWER_BOUND
                ) && windowSizeClass.isHeightAtLeastBreakpoint(
                    HEIGHT_DP_MEDIUM_LOWER_BOUND
                ) -> MOBILE_PORTRAIT
                else -> DESKTOP
            }
        }
    }
}