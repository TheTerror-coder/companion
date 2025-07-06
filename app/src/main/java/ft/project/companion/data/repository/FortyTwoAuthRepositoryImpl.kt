package ft.project.companion.data.repository

import android.content.Intent
import android.util.Log
import androidx.core.net.toUri
import ft.project.companion.TAG
import ft.project.companion.data.datasource.provision.FortyTwoAuthFromContextProvider
import ft.project.companion.data.remote.config.UltimApiAuthConfig
import ft.project.companion.domain.repository.FortyTwoAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.openid.appauth.AuthState
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ClientSecretBasic
import net.openid.appauth.TokenResponse
import javax.inject.Inject

class FortyTwoAuthRepositoryImpl @Inject constructor(
    private val localDataSource: FortyTwoAuthFromContextProvider,
): FortyTwoAuthRepository {

    private val _authServiceConfig: AuthorizationServiceConfiguration =
        AuthorizationServiceConfiguration(
            UltimApiAuthConfig.AUTH_URI.toUri(),
            UltimApiAuthConfig.TOKEN_URI.toUri()
        )
    private val _authState = MutableStateFlow(AuthState(_authServiceConfig))
    private lateinit var _authRequest: AuthorizationRequest
    private lateinit var _authService: AuthorizationService

    init {
        initAuth()
    }

    override val authState: StateFlow<AuthState>
        get() = _authState.asStateFlow()

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
        Log.d(TAG, "FortyTwoAuthRepositoryImpl*****************: initAuth() done")
    }

    private fun updateAuthState(authResponse: AuthorizationResponse?, authException: AuthorizationException?){

        val newAuthState = AuthState(_authServiceConfig).apply {
            update(authResponse, authException)
        }

        _authState.update {
            newAuthState
        }
        Log.d(TAG, "FortyTwoAuthRepositoryImpl*****************: updateAuthState():  update done: isAuthorized=${_authState.value.isAuthorized}")
    }

    private fun updateAuthState(tokenResponse: TokenResponse?, authException: AuthorizationException?){
        val newAuthState = AuthState(_authServiceConfig).apply {
            update(tokenResponse, authException)
        }

        _authState.update {
            newAuthState
        }
        Log.d(TAG, "FortyTwoAuthRepositoryImpl*****************: updateAuthState():  update done: isAuthorized=${_authState.value.isAuthorized}")
    }


    override fun exchangeToken(
        intent: Intent,
    ){
        val resp = AuthorizationResponse.fromIntent(intent)
        val ex = AuthorizationException.fromIntent(intent)

        Log.d(TAG, "FortyTwoAuthRepositoryImpl*****************: exchangeToken():  entered")
        updateAuthState(resp, ex)

        if (resp != null){
            Log.d(TAG, "FortyTwoAuthRepositoryImpl*****************: exchangeToken():  resp is not null")
            val tokenRequest = resp.createTokenExchangeRequest()
            val clientAuth = ClientSecretBasic(UltimApiAuthConfig.CLIENT_SECRET)

            _authService.performTokenRequest(tokenRequest, clientAuth){
                    tokenResp, tokenEx ->

                Log.d(TAG, "****************exchangeToken: token exchange is done")
                updateAuthState(tokenResp, tokenEx)

                if (tokenResp != null){
                    Log.d(TAG, "******************exchangeToken:*******access token: ${tokenResp.accessToken}")
                }
                else {
                    Log.d(TAG, "**********************exchangeToken:************auth exception occured: ${tokenEx?.localizedMessage}")
                }
            }
        }
        else{
            Log.d(TAG, "FortyTwoAuthRepositoryImpl*****************: exchangeToken():  resp is null")
        }
    }

}