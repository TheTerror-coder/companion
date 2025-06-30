package ft.project.companion.presentation.viewmodels

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.internal.Contexts.getApplication
import ft.project.companion.TAG
import ft.project.companion.presentation.composables.AuthenticationScreen
import ft.project.companion.presentation.composables.AuthenticationUiAction
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
import net.openid.appauth.ResponseTypeValues
import net.openid.appauth.TokenResponse
import javax.inject.Inject

object UltimApiAuthConfig {
    const val API42 = "https://api.intra.42.fr/v2"
    const val AUTH_URI = "https://api.intra.42.fr/oauth/authorize"
    const val TOKEN_URI = "https://api.intra.42.fr/oauth/token"
//    const val END_SESSION_URI = "https://github.com/logout"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "public"
    const val CLIENT_ID = "u-s4t2ud-6908f20e34d39a7d00ab03ea0e80af2fa75d597bb8660d68e29ecb0344dcb217"
    const val CLIENT_SECRET = "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"
    const val CALLBACK_URL = "com.companion.jfaye://ultimapi.com/callback"
//    const val REDIRECT_URI = "https://api.intra.42.fr/oauth/authorize?client_id=$CLIENT_ID&redirect_uri=$CALLBACK_URL&response_type=code"
//    const val LOGOUT_CALLBACK_URL = "com.companion.jfaye://ultimapi.com/logout_callback"
}

data class AuthenticationState(
    val fortyTwoShieldIsPressed: Boolean = false,
    val navigateToHome: Boolean = false,
    private val authServiceConfig: AuthorizationServiceConfiguration,
    val authState: AuthState = AuthState(authServiceConfig),
)

@HiltViewModel
class FortyTwoShieldViewModel @Inject constructor(application: Application): AndroidViewModel(application) {

    private val _authServiceConfig: AuthorizationServiceConfiguration = AuthorizationServiceConfiguration(
        Uri.parse(UltimApiAuthConfig.AUTH_URI),
        Uri.parse(UltimApiAuthConfig.TOKEN_URI)
    )
    private val _uiState = MutableStateFlow(AuthenticationState(authServiceConfig = _authServiceConfig))
//    private val _fortyTwoShieldIsPressed = mutableStateOf(false)
//    private val _navigateToHome = mutableStateOf(false)
    private val _context = application.applicationContext
    lateinit private var _authRequest: AuthorizationRequest
    lateinit private var _authService: AuthorizationService

    init {
        Log.d(TAG, "ViewModel*****************: entered init")
        initAuth(_authServiceConfig)
    }

    val uiState: StateFlow<AuthenticationState>
        get() = _uiState.asStateFlow()
//    val fortyTwoShieldIsPressed: State<Boolean>
//        get() = _fortyTwoShieldIsPressed
//    val navigateToHome: State<Boolean>
//        get() = _navigateToHome

    val authRequest: AuthorizationRequest
        get()  = _authRequest

    val authService: AuthorizationService
        get()  = _authService


    fun initAuth(
        authServiceConfig: AuthorizationServiceConfiguration = _authServiceConfig,
    ) {

//        val authServiceConfig = AuthorizationServiceConfiguration(
//            Uri.parse(UltimApiAuthConfig.AUTH_URI),
//            Uri.parse(UltimApiAuthConfig.TOKEN_URI)
//        )

        _authRequest = AuthorizationRequest.Builder(
            authServiceConfig,
            UltimApiAuthConfig.CLIENT_ID,
            UltimApiAuthConfig.RESPONSE_TYPE,
            Uri.parse(UltimApiAuthConfig.CALLBACK_URL)
        )
            .setScope(UltimApiAuthConfig.SCOPE)
            .build()

        _authService = AuthorizationService(_context)
        Log.d(TAG, "ViewModel*****************: initAuth() done")
    }

    fun performAuthTokenExchange(intent: Intent, onResult: (authResp: TokenResponse?, authEx: AuthorizationException?) -> Unit){
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

    fun updateAuthState(authResponse: AuthorizationResponse?, authException: AuthorizationException?){
        _uiState.update { currentState ->
            currentState.authState.update(authResponse, authException)
            currentState.copy(authState = currentState.authState)
        }
        Log.d(TAG, "ViewModel*****************: updateAuthState():  update done")
    }

    fun updateAuthState(tokenResponse: TokenResponse?, authException: AuthorizationException?){
        _uiState.update { currentState ->
            currentState.authState.update(tokenResponse, authException)
            currentState.copy(authState = currentState.authState)
        }
        Log.d(TAG, "ViewModel*****************: updateAuthState():  update done")
    }

    fun onAuthenticationUiAction(action: AuthenticationUiAction){
        when(action){
            is AuthenticationUiAction.onFortyTwoShieldTap -> onFortyTwoShieldTap()
            is AuthenticationUiAction.fortyTwoShieldPressed -> fortyTwoShieldPressed()
            is AuthenticationUiAction.fortyTwoShieldUnpressed -> fortyTwoShieldUnpressed()
            is AuthenticationUiAction.navigateToHome -> navigateToHome()
            is AuthenticationUiAction.navigatedToHome -> navigatedToHome()
        }
    }

    private fun onFortyTwoShieldTap() {
        _uiState.update { currentState ->
            currentState.copy(
                navigateToHome = true
            )
        }
        Log.d(TAG, "ViewModel*****************: onFortyTwoShieldTap()")
//        _navigateToHome.value = true
    }

    private fun navigateToHome() {
        _uiState.update { currentState ->
            currentState.copy(
                navigateToHome = true
            )
        }
//        _navigateToHome.value = false
        Log.d(TAG, "ViewModel*****************: navigatedToHome()")
    }

    private fun navigatedToHome() {
        _uiState.update { currentState ->
            currentState.copy(
                navigateToHome = false
            )
        }
//        _navigateToHome.value = false
        Log.d(TAG, "ViewModel*****************: navigatedToHome()")
    }

    private fun fortyTwoShieldPressed() {
        _uiState.update { currentState ->
            currentState.copy(
                fortyTwoShieldIsPressed = true
            )
        }
//        _fortyTwoShieldIsPressed.value = value
        Log.d(TAG, "ViewModel*****************: fortyTwoShieldPressed()")
    }

    private fun fortyTwoShieldUnpressed() {
        _uiState.update { currentState ->
            currentState.copy(
                fortyTwoShieldIsPressed = false
            )
        }
//        _fortyTwoShieldIsPressed.value = value
        Log.d(TAG, "ViewModel*****************: fortyTwoShieldUnpressed()")
    }

}