package ft.project.companion.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FortyTwoShieldViewModel @Inject constructor(): ViewModel() {
    private val _fortyTwoShieldIsPressed = mutableStateOf(false)

    val fortyTwoShieldIsPressed: MutableState<Boolean>
        get() = _fortyTwoShieldIsPressed

    val onFortyTwoShieldTap: () -> Unit = {}

    fun setFortyTwoShieldPressed(value: Boolean) {
        _fortyTwoShieldIsPressed.value = value
    }
}