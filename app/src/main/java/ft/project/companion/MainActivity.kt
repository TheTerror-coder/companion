package ft.project.companion

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ft.project.companion.presentation.composables.authentication.FortyTwoShieldScreen
import ft.project.companion.ui.theme.CompanionTheme


private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onStart called")

        setContent {
            CompanionTheme (
                dynamicColor = false
            ) {
//                LoginPage()
                Surface (
//                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                ) {
                    FortyTwoShieldScreen()
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
//            CompanionTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompanionTheme (
        dynamicColor = false
    ) {
//                LoginPage()
        Surface (
//                    color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
        ) {
            FortyTwoShieldScreen()
        }
    }
}