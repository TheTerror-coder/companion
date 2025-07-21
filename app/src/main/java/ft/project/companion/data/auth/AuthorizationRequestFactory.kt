package ft.project.companion.data.auth

import androidx.core.net.toUri
import ft.project.companion.data.remote.config.UltimApiAuthConfig
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationServiceConfiguration
import javax.inject.Inject

class AuthorizationRequestFactory @Inject constructor(
    private val authServiceConfig: AuthorizationServiceConfiguration,
) {
    fun create(): AuthorizationRequest{
        return AuthorizationRequest.Builder(
            authServiceConfig,
            UltimApiAuthConfig.CLIENT_ID,
            UltimApiAuthConfig.RESPONSE_TYPE,
            UltimApiAuthConfig.CALLBACK_URL.toUri()
        )
            .setScope(UltimApiAuthConfig.SCOPE)
            .build()
    }
}