package ft.project.companion.domain.repository

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import kotlinx.coroutines.flow.StateFlow
import net.openid.appauth.AuthState

interface FortyTwoAuthRepository {

    val authState: StateFlow<AuthState>

    suspend fun exchangeToken(
        intent: Intent,
    )

    suspend fun launchAuthorization(
        authActivityLauncher: ActivityResultLauncher<Intent>,
    )
}