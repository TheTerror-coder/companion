package ft.project.companion.presentation.authentication

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ft.project.companion.TAG
import ft.project.companion.data.auth.AuthorizationRequestFactory
import ft.project.companion.domain.repository.FortyTwoAuthRepository
import ft.project.companion.core.CompanionErrorManager
import ft.project.companion.core.CompanionLogger
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val fortyTwoAuthRepository: FortyTwoAuthRepository,
    private val authorizationService: AuthorizationService,
    private val authorizationRequestFactory: AuthorizationRequestFactory,
    private val errorManager: CompanionErrorManager,
): ViewModel() {

    private val _uiState = MutableStateFlow(AuthenticationState())

    init {
        Log.i(
            TAG,
            "************** AuthenticationViewModel instance Initialisation **************"
        )
        subscribeToAuthState()
    }

    val uiState: StateFlow<AuthenticationState>
        get() = _uiState.asStateFlow()

    val authService: AuthorizationService
        get() = authorizationService
    val authRequest: AuthorizationRequest
        get() = authorizationRequestFactory.create()

    private fun subscribeToAuthState() {
        viewModelScope.launch {
            try {
                fortyTwoAuthRepository.authState.collect { currentAuthState ->
                    _uiState.update { current ->
                        CompanionLogger.i(
                            msg = "collecting... -> isAuthorized = ${currentAuthState.isAuthorized}"
                        )
                        current.copy(isAuthorized = currentAuthState.isAuthorized && !currentAuthState.accessToken.isNullOrEmpty())
                    }
                }
            } catch (e: CancellationException){
                CompanionLogger.d(
                    msg = "Caught ${e.toString()}\nThen rethrew ${e.toString()}"
                )
                throw e
            } catch (e: Exception){
                CompanionLogger.logException(
                    e = e,
                    errorManager = errorManager,
                    msg = "Something went wrong during authentication state collecting"
                )
            }
        }
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
            is AuthenticationUiAction.fortyTwoShieldPressed -> fortyTwoShieldPressed()
            is AuthenticationUiAction.fortyTwoShieldUnpressed -> fortyTwoShieldUnpressed()
        }
    }

    fun performAuthTokenExchange(
        intent: Intent,
    ){
        viewModelScope.launch {
            try {
                    fortyTwoAuthRepository.exchangeToken(
                        intent = intent,
                    )
            } catch (e: CancellationException){
                CompanionLogger.d(
                    msg = "Caught ${e.toString()}\nThen rethrew ${e.toString()}"
                )
                throw e
            } catch (e: Exception){
                CompanionLogger.logException(
                    e = e,
                    errorManager = errorManager,
                    msg = "Something went wrong during Authorization"
                )
            }
        }
    }

}
