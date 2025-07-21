package ft.project.companion.domain.repository

import android.content.Intent
import kotlinx.coroutines.flow.StateFlow
import net.openid.appauth.AuthState

interface FortyTwoAuthRepository {

    val authState: StateFlow<AuthState>

    suspend fun exchangeToken(
        intent: Intent,
    )
}