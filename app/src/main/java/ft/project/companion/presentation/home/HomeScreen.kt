package ft.project.companion.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.waterfallPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import ft.project.companion.TAG
import ft.project.companion.presentation.utils.components.LocationStoneComponent
import ft.project.companion.presentation.utils.components.LoginStoneComponent
import ft.project.companion.presentation.utils.components.UserInfoFieldModelComponent
import ft.project.companion.presentation.utils.components.WalletStoneComponent
import ft.project.companion.ui.theme.backgroundDark
import ft.project.companion.utils.ScreenSpaceConfig

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
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(
                        space = 50.dp,
                        alignment = Alignment.Top
                    ),
                ) {
                    homeContent(homeUiState = homeUiState)
                }
            }
    }
}

@Composable
private fun homeContent(
    homeUiState: HomeUiState,
    modifier: Modifier = Modifier
){
    val parentHeight: Int = 1
    val topVsBottomContainersHeightRatio: Float = 1.8f
    val topContainerHeightRelativeValue: Float = (parentHeight / topVsBottomContainersHeightRatio)

/*Top Container*/
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(topContainerHeightRelativeValue),
        verticalArrangement = Arrangement.spacedBy(
            space = 5.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //42 user image
            Box(
                modifier = modifier
                    .fillMaxSize()
            ) {
                AsyncImage(
                    model = homeUiState.user?.profilePictureLink?.toUri(),
                    contentDescription = "user profile picture",
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .widthIn(100.dp, 150.dp)
                )
            }
            //user login
            LoginStoneComponent(homeUiState.user?.login.toString())
        }

        //user wallet & location
        Row (
            modifier = modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth(0.5f)
                    .padding(horizontal = 5.dp, vertical = 2.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                WalletStoneComponent(
                    modifier = modifier,
                    walletContent = homeUiState.user?.wallet.toString(),
                )
            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 2.dp),
                contentAlignment = Alignment.CenterEnd,
            ) {
                LocationStoneComponent(
                    modifier = modifier,
                    locationContent = homeUiState.user?.location.toString(),
                )
            }
        }
    }

/*Bottom Container*/
    //user email & mobile & level
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(
            space = 18.dp,
            alignment = Alignment.CenterVertically
        ),
    ) {
        //Email box
        UserInfoFieldModelComponent(
            infoType = "Email",
            infoContent = homeUiState.user?.email.toString(),
            infoIcon = Icons.Filled.Email,
            modifier = modifier,
        )
        //Mobile box
        UserInfoFieldModelComponent(
            infoType = "Mobile",
            infoContent = homeUiState.user?.mobile.toString(),
            infoIcon = Icons.Filled.PhoneAndroid,
            modifier = modifier,
        )
        //Level box
        UserInfoFieldModelComponent(
            infoType = "Level",
            infoContent = homeUiState.user?.level.toString(),
            infoIcon = Icons.Filled.Speed,
            modifier = modifier,
        )
    }
}

@Preview
@Composable
private fun Preview(){
    Surface(
        color = Color.DarkGray
    ) {
        HomeScreen(
            homeUiState = HomeUiState()
        )
    }
}
