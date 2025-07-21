package ft.project.companion.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ft.project.companion.TAG
import ft.project.companion.core.CompanionErrorManager
import ft.project.companion.domain.model.UserModel
import ft.project.companion.domain.repository.UserRepository
import ft.project.companion.core.CompanionLogger
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val user: UserModel? = null,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val errorManager: CompanionErrorManager,
): ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())

    val homeUiState: StateFlow<HomeUiState>
        get() = _homeUiState.asStateFlow()

    init {
        Log.i(
            TAG,
            "************** HomeViewModel instance Initialisation **************"
        )
        subscribeToUser()
    }


    private fun subscribeToUser(){
        viewModelScope.launch {
            try {
                userRepository.user.collect { collected ->
                    CompanionLogger.i(
                        msg = "collecting... -> user = ${collected?.login.toString()}"
                    )
                    if (collected != null){
                        _homeUiState.update { current ->
                            current.copy(
                                user = collected
                            )
                        }
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
                    msg = "Something went wrong while collecting user information"
                )
            }

        }
    }

    private fun fetchUser(){
        viewModelScope.launch {
            try {
                userRepository.fetchUserInformation(refresh = false)
            } catch (e: CancellationException){
                CompanionLogger.d(
                    msg = "Caught ${e.toString()}\nThen rethrew ${e.toString()}"
                )
                throw e
            } catch (e: Exception){
                CompanionLogger.logException(
                    e = e,
                    errorManager = errorManager,
                    msg = "Something went wrong while fetching user information"
                )
            }
        }
    }

}