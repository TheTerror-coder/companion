package ft.project.companion.data.repository

import android.content.Intent
import android.util.Log
import ft.project.companion.TAG
import ft.project.companion.data.datasource.local.FortyTwoAuthLocalDataSource
import ft.project.companion.data.remote.config.UltimApiAuthConfig
import ft.project.companion.domain.fortytwoauth.FortyTwoAuthRepository
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ClientSecretBasic
import net.openid.appauth.TokenResponse
import javax.inject.Inject
import androidx.core.net.toUri

class FortyTwoAuthRepositoryImpl @Inject constructor(
    private val localDataSource: FortyTwoAuthLocalDataSource
): FortyTwoAuthRepository {

    private val _authServiceConfig: AuthorizationServiceConfiguration =
        AuthorizationServiceConfiguration(
            UltimApiAuthConfig.AUTH_URI.toUri(),
            UltimApiAuthConfig.TOKEN_URI.toUri()
        )
    private lateinit var _authRequest: AuthorizationRequest
    private lateinit var _authService: AuthorizationService

    init {
        initAuth()
    }

    override val getAuthServiceConfig: AuthorizationServiceConfiguration
        get() = _authServiceConfig

    override val getAuthRequest: AuthorizationRequest
        get() = _authRequest

    override val getAuthService: AuthorizationService
        get() = _authService

    private fun initAuth(
    ) {

        _authRequest = AuthorizationRequest.Builder(
            _authServiceConfig,
            UltimApiAuthConfig.CLIENT_ID,
            UltimApiAuthConfig.RESPONSE_TYPE,
            UltimApiAuthConfig.CALLBACK_URL.toUri()
        )
            .setScope(UltimApiAuthConfig.SCOPE)
            .build()

        _authService = localDataSource.provideAuthService()
        Log.d(TAG, "ViewModel*****************: initAuth() done")
    }


    override fun exchangeToken(
        intent: Intent,
        updateAuthState: (resp: AuthorizationResponse?, ex: AuthorizationException?) -> Unit,
        onResult: (authResp: TokenResponse?, authEx: AuthorizationException?) -> Unit
    ){
        val resp = AuthorizationResponse.fromIntent(intent)
        val ex = AuthorizationException.fromIntent(intent)

        Log.d(TAG, "ViewModel*****************: performAuthTokenExchange():  entered")
        updateAuthState(resp, ex)

        if (resp != null){
            Log.d(TAG, "ViewModel*****************: performAuthTokenExchange():  resp is not null")
            val tokenRequest = resp.createTokenExchangeRequest()
            val clientAuth = ClientSecretBasic(UltimApiAuthConfig.CLIENT_SECRET)

            _authService.performTokenRequest(tokenRequest, clientAuth){
                    tokenResp, tokenEx ->
                onResult(tokenResp, tokenEx)
            }
        }
        else{
            Log.d(TAG, "ViewModel*****************: performAuthTokenExchange():  resp is null")
            onResult(null, ex)
        }
    }

}