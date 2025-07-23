package ft.project.companion.data.repository

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import ft.project.companion.TAG
import ft.project.companion.core.CompanionErrorManager
import ft.project.companion.core.CompanionLogger
import ft.project.companion.core.copy
import ft.project.companion.data.auth.AuthorizationRequestFactory
import ft.project.companion.domain.datasource.AuthDataStore
import ft.project.companion.data.di.IoDispatcher
import ft.project.companion.domain.repository.FortyTwoAuthRepository
import ft.project.companion.domain.repository.UserRepository
import ft.project.companion.domain.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import net.openid.appauth.AuthState
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ClientSecretBasic
import net.openid.appauth.TokenResponse
import java.lang.IllegalStateException
import javax.inject.Inject

class FortyTwoAuthRepositoryImpl @Inject constructor(
    private val authorizationService: AuthorizationService,
    private val authServiceConfig: AuthorizationServiceConfiguration,
    private val authRequestFactory: AuthorizationRequestFactory,
    private val authDataStore: AuthDataStore,
    private val userRepository: UserRepository,
    private val clientAuth: ClientSecretBasic,
    private val errorManager: CompanionErrorManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
): FortyTwoAuthRepository {

    private val _authState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState(authServiceConfig))
    private val _authStateMutex = Mutex()

    override val authState: StateFlow<AuthState>
        get() = _authState.asStateFlow()

    init {
        Log.i(
            TAG,
            "************** FortyTwoAuthRepositoryImpl instance Initialisation **************"
        )
    }

    private suspend fun doUpdate(
        authResponse: AuthorizationResponse? = null,
        tokenResponse: TokenResponse? = null,
        authException: AuthorizationException?
    ){

        try {
            require(authResponse != null || tokenResponse != null)
        } catch (e: IllegalArgumentException){
            val msg = e.localizedMessage
            throw IllegalArgumentException("$msg\nThrown from FortyTwoAuthRepositoryImpl.doUpdate() function", e)
        }

        _authStateMutex.withLock {
            val newAuthState = _authState.value.copy()
            if (newAuthState == null){
                CompanionLogger.e(
                    msg = "Error: failed to update AuthState"
                )
                return
            }
            if (authResponse != null){
                newAuthState.update(authResponse, authException)
            } else {
                newAuthState.update(tokenResponse, authException)
            }
            _authState.update {
                newAuthState
            }
            authDataStore.saveAuthState(_authState.value)
            CompanionLogger.d(
                msg = "SUCCESS: updated AuthState"
            )
        }
    }

    private suspend fun updateAuthState(
        authResponse: AuthorizationResponse?,
        authException: AuthorizationException?
    ){
        doUpdate(authResponse = authResponse, authException = authException)
    }

    private suspend fun updateAuthState(
        tokenResponse: TokenResponse?,
        authException: AuthorizationException?
    ){
        doUpdate(tokenResponse = tokenResponse, authException = authException)
    }

    override suspend fun launchAuthorization(authActivityLauncher: ActivityResultLauncher<Intent>) {
        try {
            when (val result = authDataStore.fetchAuthState()){
                is Result.Success -> {
                    _authState.update {
                        result.value
                    }
                    Log.d(TAG, "****************launchAuthorization: restored authState from DataStore")
                    userRepository.fetchUserInformation(refresh = true)
                }
                is Result.Error -> {
                    Log.d(TAG, "****************launchAuthorization: could not restore authState from DataStore")
                    authActivityLauncher.launch(authorizationService.getAuthorizationRequestIntent(
                        authRequestFactory.create()
                    ))
                }
            }
        } catch (e: IllegalStateException){
            CompanionLogger.logException(
                e = e,
                errorManager = errorManager,
                msg = "Restored AuthState from DataStore lacks Authorization response"
            )
            Log.i(TAG, "****************launchAuthorization: relaunching...")
            authActivityLauncher.launch(authorizationService.getAuthorizationRequestIntent(
                authRequestFactory.create()
            ))
        }
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