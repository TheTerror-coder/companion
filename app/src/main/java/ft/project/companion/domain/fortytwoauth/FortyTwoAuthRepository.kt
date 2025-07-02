package ft.project.companion.domain.fortytwoauth

import android.content.Intent
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ft.project.companion.data.repository.FortyTwoAuthRepositoryImpl
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.TokenResponse

interface FortyTwoAuthRepository {

    val getAuthServiceConfig: AuthorizationServiceConfiguration

    val getAuthRequest: AuthorizationRequest

    val getAuthService: AuthorizationService

    fun exchangeToken(
        intent: Intent,
        updateAuthState: (resp: AuthorizationResponse?, ex: AuthorizationException?) -> Unit,
        onResult: (authResp: TokenResponse?, authEx: AuthorizationException?) -> Unit,
    )
}