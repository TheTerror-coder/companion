package ft.project.companion.domain.repository

import android.content.Intent
import kotlinx.coroutines.flow.StateFlow
import net.openid.appauth.AuthState
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration

interface FortyTwoAuthRepository {

    val authState: StateFlow<AuthState>

    val getAuthServiceConfig: AuthorizationServiceConfiguration

    val getAuthRequest: AuthorizationRequest

    val getAuthService: AuthorizationService

    fun exchangeToken(
        intent: Intent,
    )
}