package ft.project.companion.presentation.authentication

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ft.project.companion.TAG
import ft.project.companion.domain.repository.FortyTwoAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import javax.inject.Inject

data class AuthenticationState(
    val fortyTwoShieldIsPressed: Boolean = false,
    val isAuthorized: Boolean = false,
)

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val fortyTwoAuthRepository: FortyTwoAuthRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(AuthenticationState())

    init {
        viewModelScope.launch {
            fortyTwoAuthRepository.authState
                .onEach { anAuthState ->
                    _uiState.update { anAuthenticationState ->
                        Log.d(
                            TAG,
                            "ViewModel*****************: AuthenticationViewModel: init{}: authState update: isAuthorized = ${anAuthState.isAuthorized}"
                        )
                        anAuthenticationState.copy(isAuthorized = anAuthState.isAuthorized && !anAuthState.accessToken.isNullOrEmpty())
                    }
                }
                .catch { e ->
                    Log.d(
                        TAG,
                        "ViewModel*****************: AuthenticationViewModel: init{}: Exception took place-->: ${e.localizedMessage}"
                    )
                }
                .collect()
        }
    }

    val uiState: StateFlow<AuthenticationState>
        get() = _uiState.asStateFlow()

    val authRequest: AuthorizationRequest
        get() = fortyTwoAuthRepository.getAuthRequest

    val authService: AuthorizationService
        get()  = fortyTwoAuthRepository.getAuthService

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
            is AuthenticationUiAction.fortyTwoShieldPressed -> fortyTwoShieldPressed()
            is AuthenticationUiAction.fortyTwoShieldUnpressed -> fortyTwoShieldUnpressed()
        }
    }

    fun performAuthTokenExchange(
        intent: Intent,
    ){
        fortyTwoAuthRepository.exchangeToken(
            intent = intent,
        )
    }

}