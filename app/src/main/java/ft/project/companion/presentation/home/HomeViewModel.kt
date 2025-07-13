package ft.project.companion.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ft.project.companion.TAG
import ft.project.companion.domain.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
): ViewModel() {

    init {
        viewModelScope.launch {
            val user = userRepository.refreshUserInformation()

            Log.d(
                TAG,
                "-----------------user: ${user.login} \n" +
                        "-----------------emaim: ${user.email} \n" +
                        "-----------------mobile: ${user.mobile} \n" +
                        "-----------------wallet: ${user.wallet} \n" +
                        "-----------------location: ${user.location} \n" +
                        "-----------------level: ${user.level} "
            )
        }
    }

}