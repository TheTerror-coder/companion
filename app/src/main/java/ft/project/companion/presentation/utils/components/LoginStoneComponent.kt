package ft.project.companion.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import ft.project.companion.presentation.utils.components.companionvectorspack.MyIconPack
import ft.project.companion.presentation.utils.components.companionvectorspack.myiconpack.Loginstone
import ft.project.companion.presentation.utils.ui.MeasureDefaults

@Composable
fun LoginStoneComponent(
    login: String,
    modifier: Modifier = Modifier,
    stoneColor: Color = MaterialTheme.colorScheme.primary
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (
                stoneRef,
                textRef
            ) = createRefs()

            val stoneStartGuideline = createGuidelineFromStart(0.dp)
            val stoneTopGuideline = createGuidelineFromTop(0.dp)
            val stoneEndGuideline = createGuidelineFromEnd(0.dp)
            val stoneBottomGuideline = createGuidelineFromBottom(0.dp)

            val textStartGuideline = createGuidelineFromStart(0.dp)
            val textTopGuideline = createGuidelineFromTop(0.5f)
            val textEndGuideline = createGuidelineFromEnd(0.dp)
            val textBottomGuideline = createGuidelineFromBottom(0.06f)

            Image(
                imageVector = MyIconPack.Loginstone,
                contentDescription = "",
                colorFilter = ColorFilter.tint(stoneColor),
                modifier = Modifier
                    .fillMaxWidth(MeasureDefaults.FRACTION_8F)
                    .constrainAs(stoneRef){
                        start.linkTo(stoneStartGuideline)
                        top.linkTo(stoneTopGuideline)
                        end.linkTo(stoneEndGuideline)
                        bottom.linkTo(stoneBottomGuideline)
                    }
            )

            SingleLineTextComponent(
                modifier = Modifier
                    .fillMaxWidth(MeasureDefaults.FRACTION_4F)
                    .constrainAs(textRef){
//                        start.linkTo(textStartGuideline)
//                        top.linkTo(textTopGuideline)
//                        end.linkTo(textEndGuideline)
                        bottom.linkTo(textBottomGuideline)
                        centerHorizontallyTo(stoneRef)
                    },
                text = login,
                color = MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.titleLarge,
                autoSize = TextAutoSize.StepBased(stepSize = MeasureDefaults.TEXT_UNIT_5SP)
            )
        }
    }
}

@Preview
@Composable
fun LoginStoneComponentPreview() {
    Surface(
        modifier = Modifier
            .width(400.dp),
        color =(Color.Gray)
    ) {
        LoginStoneComponent(login = "jfaye")

    }
}