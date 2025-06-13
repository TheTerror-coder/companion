package ft.project.companion.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import ft.project.companion.presentation.navigation.Home
import javax.inject.Inject

@HiltViewModel
class FortyTwoShieldViewModel @Inject constructor(): ViewModel() {
    private val _fortyTwoShieldIsPressed = mutableStateOf(false)
    private val _navigateToHome = mutableStateOf(false)

    val fortyTwoShieldIsPressed: MutableState<Boolean>
        get() = _fortyTwoShieldIsPressed
    val navigateToHome: MutableState<Boolean>
        get() = _navigateToHome

    val onFortyTwoShieldTap: () -> Unit = {
        _navigateToHome.value = true
    }

    fun navigatedToHome() {
        _navigateToHome.value = false
    }

    fun setFortyTwoShieldPressed(value: Boolean) {
        _fortyTwoShieldIsPressed.value = value
    }
}