package ft.project.companion.presentation.home

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ft.project.companion.presentation.utils.components.LocationStoneComponent
import ft.project.companion.presentation.utils.components.LoginStoneComponent
import ft.project.companion.presentation.utils.components.UserInfoFieldModelComponent
import ft.project.companion.presentation.utils.components.WalletStoneComponent

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 50.dp,
            alignment = Alignment.CenterVertically
        ),
    ) {
        val wallet = "1000"
        val location = "Lyon"

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
            LoginStoneComponent()

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
                        walletContent = wallet,
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
                        locationContent = location,
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
                infoContent = "johnnydoe12345@gmail.com",
                infoIcon = Icons.Filled.Email,
                modifier = modifier,
            )
            //Mobile box
            UserInfoFieldModelComponent(
                infoType = "Mobile",
                infoContent = "+33 X XX XX XX XX",
                infoIcon = Icons.Filled.PhoneAndroid,
                modifier = modifier,
            )
            //Level box
            UserInfoFieldModelComponent(
                infoType = "Level",
                infoContent = "13.6%",
                infoIcon = Icons.Filled.Speed,
                modifier = modifier,
            )
        }
    }
}
