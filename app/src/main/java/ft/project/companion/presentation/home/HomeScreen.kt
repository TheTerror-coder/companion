package ft.project.companion.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Speed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ft.project.companion.TAG
import ft.project.companion.presentation.utils.components.LocationStoneComponent
import ft.project.companion.presentation.utils.components.LoginStoneComponent
import ft.project.companion.presentation.utils.components.UserInfoFieldModelComponent
import ft.project.companion.presentation.utils.components.WalletStoneComponent

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

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 50.dp,
            alignment = Alignment.CenterVertically
        ),
    ) {

/*Top Container*/
        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(
                space = 5.dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //user login
            LoginStoneComponent(homeUiState.user?.login.toString())

            //user wallet & location
            Row (
                modifier = modifier.fillMaxWidth()
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
            modifier = modifier.fillMaxWidth(),
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
}
