package ft.project.companion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ft.project.companion.presentation.authentication.AuthenticationViewModel
import ft.project.companion.ui.theme.CompanionTheme
import kotlin.getValue


const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val _viewModel: AuthenticationViewModel by viewModels<AuthenticationViewModel>()

    private lateinit var _authActivityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")
        Log.d(TAG, "***********DEBUG: client_id=${BuildConfig.CLIENT_ID}")
        Log.d(TAG, "***********DEBUG: client_secret=${BuildConfig.CLIENT_SECRET}")

        _authActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
            Log.d(TAG, "****************registerForActivityResult(): callback entered")
            val intent = result.data ?: return@registerForActivityResult
            _viewModel.performAuthTokenExchange(intent)

        }


        setContent {
            CompanionTheme (
                dynamicColor = false
            ) {
                Surface (
//                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                ) {
                    val authUiState by _viewModel.uiState.collectAsState()

                    CompanionNavigation(
                        authUiState = authUiState,
                        onAuthUiAction = _viewModel::onAuthenticationUiAction,
                        onFortyTwoShieldClick = {
                Log.d(TAG, "****************onFortyTwoShieldClick: lamda is entered")
                            _authActivityLauncher.launch(_viewModel.authService.getAuthorizationRequestIntent(
                                _viewModel.authRequest
                            ))
                Log.d(TAG, "****************onFortyTwoShieldClick: lamda is exited")
                        }
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }

}
