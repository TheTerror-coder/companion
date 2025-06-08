package ft.project.companion.ui.design

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
public fun LoginScreen(modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoginField(state = rememberTextFieldState())
        PasswordField(state = rememberTextFieldState())
        LoginValidationButton(onClick = {
            Log.d("LoginScreen","Continue Button clicked")
        })
    }
}

@Composable
public fun LoginField(modififer: Modifier = Modifier, state: TextFieldState) {
    TextField(
        state = state,
        placeholder = { Text("enter your login") },
        label = { Text("Login") },
        lineLimits = TextFieldLineLimits.SingleLine,
        textStyle = TextStyle(fontSize = 50.sp),
        modifier = Modifier
            .fillMaxWidth(0.8f)
//            .height(70.dp)
    )
}

@Composable
public fun PasswordField(modififer: Modifier = Modifier, state: TextFieldState) {
    var passwordIsVisible by remember { mutableStateOf(false) }

    SecureTextField(
        state = state,
        placeholder = { Text("xxxxxx") },
        label = { Text("Password") },
        trailingIcon = {
            if (passwordIsVisible) {
                Icon(imageVector = Icons.Filled.VisibilityOff,
                    contentDescription = "hide password icon",
                    modifier = Modifier.clickable { passwordIsVisible = !passwordIsVisible },
                )
            } else {
                Icon(imageVector = Icons.Filled.Visibility,
                    contentDescription = "unhide password icon",
                    modifier = Modifier.clickable { passwordIsVisible = !passwordIsVisible },
                )
            } },
        textObfuscationMode = if (passwordIsVisible) TextObfuscationMode.Visible else TextObfuscationMode.RevealLastTyped,
        textObfuscationCharacter = 'x',
        keyboardOptions = KeyboardOptions (
            keyboardType = KeyboardType.Password,
        ),
    )
}

@Composable
public fun LoginValidationButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    OutlinedButton (
        onClick = onClick,
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            disabledContentColor = MaterialTheme.colorScheme.onSecondary
        )
    ) {
        Text("Continue")
    }
}