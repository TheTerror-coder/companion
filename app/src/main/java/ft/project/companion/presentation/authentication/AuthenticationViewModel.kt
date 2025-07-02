package ft.project.companion.presentation.authentication

import android.content.Intent
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import ft.project.companion.TAG
import ft.project.companion.domain.fortytwoauth.FortyTwoAuthRepository
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
import net.openid.appauth.TokenResponse
import javax.inject.Inject

data class AuthenticationState(
    val fortyTwoShieldIsPressed: Boolean = false,
    val navigateToHome: Boolean = false,
    private val authServiceConfig: AuthorizationServiceConfiguration,
    val authState: AuthState = AuthState(authServiceConfig),
)

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val fortyTwoAuthRepository: FortyTwoAuthRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(AuthenticationState(authServiceConfig = fortyTwoAuthRepository.getAuthServiceConfig))

    val uiState: StateFlow<AuthenticationState>
        get() = _uiState.asStateFlow()

    val authRequest: AuthorizationRequest
        get() = fortyTwoAuthRepository.getAuthRequest

    val authService: AuthorizationService
        get()  = fortyTwoAuthRepository.getAuthService

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

    private fun onFortyTwoShieldTap() {
        _uiState.update { currentState ->
            currentState.copy(
                navigateToHome = true
            )
        }
        Log.d(TAG, "ViewModel*****************: onFortyTwoShieldTap()")
    }

    private fun navigateToHome() {
        _uiState.update { currentState ->
            currentState.copy(
                navigateToHome = true
            )
        }
        Log.d(TAG, "ViewModel*****************: navigatedToHome()")
    }

    private fun navigatedToHome() {
        _uiState.update { currentState ->
            currentState.copy(
                navigateToHome = false
            )
        }
        Log.d(TAG, "ViewModel*****************: navigatedToHome()")
    }

    private fun fortyTwoShieldPressed() {
        _uiState.update { currentState ->
            currentState.copy(
                fortyTwoShieldIsPressed = true
            )
        }
        Log.d(TAG, "ViewModel*****************: fortyTwoShieldPressed()")
    }

    private fun fortyTwoShieldUnpressed() {
        _uiState.update { currentState ->
            currentState.copy(
                fortyTwoShieldIsPressed = false
            )
        }
        Log.d(TAG, "ViewModel*****************: fortyTwoShieldUnpressed()")
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

    fun performAuthTokenExchange(
        intent: Intent,
    ){
        fortyTwoAuthRepository.exchangeToken(intent = intent, updateAuthState = ::updateAuthState) {
                tokenResp, tokenEx ->

            Log.d(TAG, "****************performAuthTokenExchange: token exchange is done")
            updateAuthState(tokenResp, tokenEx)

            if (tokenResp != null){
                Log.d(TAG, "******************performAuthTokenExchange:*******access token: ${tokenResp.accessToken}")
                onAuthenticationUiAction(AuthenticationUiAction.navigateToHome)
            }
            else {
                Log.d(TAG, "**********************performAuthTokenExchange:************auth exception occured: ${tokenEx?.localizedMessage}")
            }

        }
    }

}