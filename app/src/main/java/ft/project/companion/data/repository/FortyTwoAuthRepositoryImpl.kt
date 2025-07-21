package ft.project.companion.data.repository

import android.content.Intent
import android.util.Log
import ft.project.companion.TAG
import ft.project.companion.core.CompanionErrorManager
import ft.project.companion.core.CompanionLogger
import ft.project.companion.data.datasource.datastore.AuthDataStore
import ft.project.companion.data.di.IoDispatcher
import ft.project.companion.domain.repository.FortyTwoAuthRepository
import ft.project.companion.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.openid.appauth.AuthState
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ClientSecretBasic
import net.openid.appauth.TokenResponse
import javax.inject.Inject

class FortyTwoAuthRepositoryImpl @Inject constructor(
    private val authorizationService: AuthorizationService,
    private val authServiceConfig: AuthorizationServiceConfiguration,
    private val authDataStore: AuthDataStore,
    private val userRepository: UserRepository,
    private val clientAuth: ClientSecretBasic,
    private val errorManager: CompanionErrorManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
): FortyTwoAuthRepository {

    private val _authState = MutableStateFlow(AuthState(authServiceConfig))

    override val authState: StateFlow<AuthState>
        get() = _authState.asStateFlow()

    init {
        Log.i(
            TAG,
            "************** FortyTwoAuthRepositoryImpl instance Initialisation **************"
        )
    }

    private suspend fun updateAuthState(authResponse: AuthorizationResponse?, authException: AuthorizationException?){

        val newAuthState = AuthState(authServiceConfig).apply {
            update(authResponse, authException)
        }

        _authState.update {
            newAuthState
        }
        authDataStore.saveAuthState(newAuthState)
        CompanionLogger.d(
            msg = "update done: isAuthorized=${_authState.value.isAuthorized}"
        )
    }

    private suspend fun updateAuthState(tokenResponse: TokenResponse?, authException: AuthorizationException?){
        val newAuthState = AuthState(authServiceConfig).apply {
            update(tokenResponse, authException)
        }

        _authState.update {
            newAuthState
        }
        authDataStore.saveAuthState(newAuthState)
        CompanionLogger.d(
            msg = "update done: isAuthorized=${_authState.value.isAuthorized}"
        )
    }

    override suspend fun exchangeToken(
        intent: Intent,
    ){
        val resp = AuthorizationResponse.fromIntent(intent)
        val ex = AuthorizationException.fromIntent(intent)

        updateAuthState(resp, ex)

        if (resp != null){
            CompanionLogger.d(
                msg = "resp is not null"
            )
            val tokenRequest = resp.createTokenExchangeRequest()

            authorizationService.performTokenRequest(
                tokenRequest,
                clientAuth,
            ) { tokenResp, tokenEx ->
                CoroutineScope(ioDispatcher).launch{
                    CompanionLogger.d(
                        msg = "token exchange is done"
                    )

                    updateAuthState(tokenResp, tokenEx)

                    if (tokenResp != null){
                        userRepository.fetchUserInformation()
                    } else {
                        val msg = "AuthorizationException: Authorization failure during token request"
                        CompanionLogger.e(
                            msg = "Error: $msg\n" +
                                    "AuthorizationException thrown during token request: ${tokenEx?.localizedMessage}"
                        )
                        errorManager.emitError(msg = msg)
                    }
                }
            }
        } else {
            val msg = "AuthorizationException: Authorization failure during authorization request"
            CompanionLogger.e(
                msg = "Error: $msg\n" +
                        "AuthorizationException thrown during authorization request: ${ex?.localizedMessage}"
            )
            errorManager.emitError(msg = msg)
        }
    }

}