package ft.project.companion.presentation.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutBaseScope
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import ft.project.companion.R
import ft.project.companion.TAG
import ft.project.companion.presentation.utils.components.LocationStoneComponent
import ft.project.companion.presentation.utils.components.LoginStoneComponent
import ft.project.companion.presentation.utils.components.UserInfoFieldModelComponent
import ft.project.companion.presentation.utils.components.WalletStoneComponent
import ft.project.companion.presentation.utils.extensions.computeFontSize
import ft.project.companion.presentation.utils.ui.MeasureDefaults
import ft.project.companion.utils.ScreenSpaceConfig
import kotlin.math.log

private object LayoutIds{
    private var i = 0

//    val USER_PROFILE_PICTURE: String = "${i++}"
//    val LOGIN_STONE_COMPONENT: String = "${i++}"
    val LOGIN_STONE_BLOCK: String = "${i++}"
    val ROW_WALLET_LOCATION: String = "${i++}"
    val COLUMN_EMAIL_AND_OTHERS: String = "${i++}"
    val TOP_BOTTOM_BLOCKS_SPACER: String = "${i++}"
}

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    modifier: Modifier = Modifier
) {
    Log.d(
        TAG,
        "-----------------user: ${homeUiState.user?.login}\n" +
                "-----------------email: ${homeUiState.user?.email}\n" +
                "-----------------mobile: ${homeUiState.user?.mobile}\n" +
                "-----------------wallet: ${homeUiState.user?.wallet}\n" +
                "-----------------location: ${homeUiState.user?.location}\n" +
                "-----------------level: ${homeUiState.user?.level}"
    )

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val screenSpaceConfig = ScreenSpaceConfig.fromWindowSizeClass(windowSizeClass = windowSizeClass)

    when {
        screenSpaceConfig == ScreenSpaceConfig.MOBILE_PORTRAIT ||
        screenSpaceConfig == ScreenSpaceConfig.MOBILE_LANDSCAPE ||
        screenSpaceConfig == ScreenSpaceConfig.TABLET_PORTRAIT ||
        screenSpaceConfig == ScreenSpaceConfig.TABLET_LANDSCAPE ||
        screenSpaceConfig == ScreenSpaceConfig.DESKTOP
            -> {
                HomeContent(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp, vertical = 30.dp)
                        .verticalScroll(
                            rememberScrollState()
                        ),
                    homeUiState = homeUiState
                )
            }
    }
}

@Composable
private fun HomeContent(
    homeUiState: HomeUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        val constraintSet = decoupledConstraints()
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize(),
            constraintSet = constraintSet
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId(LayoutIds.LOGIN_STONE_BLOCK),
            ) {
                val (
                    loginStoneRef,
                    pictureRef
                ) = createRefs()

                val pictureCanvaStartGuideline = createGuidelineFromStart(0.21f)
                val pictureCanvaTopGuideline = createGuidelineFromTop(0.04f)

                val loginStoneCanvaStartGuideline = createGuidelineFromStart(0.dp)
                val loginStoneCanvaTopGuideline = createGuidelineFromTop(0.dp)
                val loginStoneCanvaEndGuideline = createGuidelineFromEnd(0.dp)
                val loginStoneCanvaBottomGuideline = createGuidelineFromBottom(0.dp)

                //42 user login
                LoginStoneComponent(
                    modifier = Modifier
//                        .layoutId(LayoutIds.LOGIN_STONE_COMPONENT),
                        .constrainAs(loginStoneRef){
                            start.linkTo(loginStoneCanvaStartGuideline)
                            top.linkTo(loginStoneCanvaTopGuideline)
                            end.linkTo(loginStoneCanvaEndGuideline)
                            bottom.linkTo(loginStoneCanvaBottomGuideline)
                            centerHorizontallyTo(parent)
                        },
                    login = homeUiState.user?.login.toString()
                )

                //42 user profile picture
                Box(
                    modifier = Modifier
                        .widthIn(80.dp, 170.dp)
                        .fillMaxWidth(MeasureDefaults.FRACTION_8F)
                        .height(IntrinsicSize.Max)
                        .clip(CircleShape)
                        .constrainAs(pictureRef){
                            start.linkTo(pictureCanvaStartGuideline)
                            top.linkTo(pictureCanvaTopGuideline)
                        }
//                        .layoutId(LayoutIds.USER_PROFILE_PICTURE)
                        .border(
                            border = BorderStroke(3.dp, MaterialTheme.colorScheme.outline),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ){
                    val pictureModifier = Modifier
                        .fillMaxHeight(MeasureDefaults.FRACTION_8F)
                        .clip(CircleShape)
                        .border(border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary), shape = CircleShape)

                    if (homeUiState.user?.profilePictureLink.isNullOrEmpty()){
                        Image(
                            painter = painterResource(R.drawable.default_avatar),
                            contentDescription = "",
                            modifier = pictureModifier
                        )
                    } else {
                        AsyncImage(
                            model = homeUiState.user.profilePictureLink.toUri(),
                            contentDescription = "user profile picture",
                            modifier = pictureModifier
                        )

                    }
                }
            }

            //row wallet-location
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId(LayoutIds.ROW_WALLET_LOCATION),
                contentAlignment = Alignment.Center
            ) {
                val rowHeight = maxWidth * MeasureDefaults.FRACTION_3F
                val rowFontSize = maxWidth.computeFontSize(0.12f)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(rowHeight),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    //42 user wallet
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(MeasureDefaults.FRACTION_5F),
                        contentAlignment = Alignment.CenterStart
                    ){

                        WalletStoneComponent(
                            modifier = Modifier
                                .fillMaxHeight(),
                            walletContent = homeUiState.user?.wallet.toString(),
                            fontSize = rowFontSize
                        )

                    }

                    //42 user location
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        LocationStoneComponent(
                            modifier = Modifier
                                .fillMaxHeight(),
                            locationContent = homeUiState.user?.location.toString(),
                            fontSize = rowFontSize
                        )
                    }
                }
            }

            //top bottom blocks containers spacer
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .layoutId(LayoutIds.TOP_BOTTOM_BLOCKS_SPACER)
            )

            //column email-mobile-level
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId(LayoutIds.COLUMN_EMAIL_AND_OTHERS),
                contentAlignment = Alignment.Center
            ) {
                val childElementHeight = maxWidth * MeasureDefaults.FRACTION_2F
                val childElementFontSize = maxWidth.computeFontSize(MeasureDefaults.FRACTION_1F)

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    //Email box
                    UserInfoFieldModelComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(childElementHeight),
                        infoType = "Email",
                        infoContent = homeUiState.user?.email.toString(),
                        infoIcon = Icons.Filled.Email,
                        fontSize =childElementFontSize
                    )
                    //Mobile box
                    UserInfoFieldModelComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(childElementHeight),
                        infoType = "Mobile",
                        infoContent = homeUiState.user?.mobile.toString(),
                        infoIcon = Icons.Filled.PhoneAndroid,
                        fontSize =childElementFontSize
                    )
                    //Level box
                    UserInfoFieldModelComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(childElementHeight),
                        infoType = "Level",
                        infoContent = homeUiState.user?.level.toString(),
                        infoIcon = Icons.Filled.Speed,
                        fontSize =childElementFontSize
                    )

                }

            }
        }

    }
}

private fun decoupledConstraints(): ConstraintSet{
    return ConstraintSet{
        val (
//            userProfilePictureRef,
//            loginStoneComponentRef,
            loginStoneBlockRef,
            rowWalletLocationRef,
            columnEmailAndOthersRef,
            topBottomBlocksSpacerRef,
        ) = createRefsFor(
//            LayoutIds.USER_PROFILE_PICTURE,
//            LayoutIds.LOGIN_STONE_COMPONENT,
            LayoutIds.LOGIN_STONE_BLOCK,
            LayoutIds.ROW_WALLET_LOCATION,
            LayoutIds.COLUMN_EMAIL_AND_OTHERS,
            LayoutIds.TOP_BOTTOM_BLOCKS_SPACER,
        )

        val startGuideline = createGuidelineFromStart(0f)
        val topGuideline = createGuidelineFromTop(0f)
        val endGuideline = createGuidelineFromEnd(0f)
        val bottomGuideline = createGuidelineFromBottom(0f)

        fun ConstrainScope.linkToSurroundings(
            startAnchor: ConstraintLayoutBaseScope.VerticalAnchor? = null,
            topAnchor: ConstraintLayoutBaseScope.HorizontalAnchor? = null,
            endAnchor: ConstraintLayoutBaseScope.VerticalAnchor? = null,
            bottomAnchor: ConstraintLayoutBaseScope.HorizontalAnchor? = null
        ){
            if (startAnchor != null){ start.linkTo(startAnchor) }
            if  (topAnchor != null){ top.linkTo(topAnchor) }
            if  (endAnchor != null){ end.linkTo(endAnchor) }
            if  (bottomAnchor != null){ bottom.linkTo(bottomAnchor) }
        }

//        constrain(userProfilePictureRef){
//            linkToSurroundings(
//                startAnchor = startGuideline,
//                topAnchor = topGuideline,
//                endAnchor = endGuideline
//            )
//            centerHorizontallyTo(parent)
//        }
//        constrain(loginStoneComponentRef){
//            linkToSurroundings(
//                startAnchor = startGuideline,
//                topAnchor = userProfilePictureRef.bottom,
//                endAnchor = endGuideline
//            )
//            centerHorizontallyTo(parent)
//        }
        constrain(loginStoneBlockRef){
            linkToSurroundings(
                startAnchor = startGuideline,
                topAnchor = topGuideline,
                endAnchor = endGuideline
            )
            centerHorizontallyTo(parent)
        }
        constrain(rowWalletLocationRef){
            linkToSurroundings(
                startAnchor = startGuideline,
                topAnchor = loginStoneBlockRef.bottom,
                endAnchor = endGuideline
            )
        }
        constrain(topBottomBlocksSpacerRef){
            linkToSurroundings(topAnchor = rowWalletLocationRef.bottom)
        }
        constrain(columnEmailAndOthersRef){
            linkToSurroundings(topAnchor = topBottomBlocksSpacerRef.bottom)
        }

    }
}

@Preview
@Composable
private fun Preview(){
    Surface(
        modifier = Modifier
            .size(600.dp, 900.dp),
        color = Color.DarkGray
    ) {
        HomeScreen(
            homeUiState = HomeUiState()
        )
    }
}
