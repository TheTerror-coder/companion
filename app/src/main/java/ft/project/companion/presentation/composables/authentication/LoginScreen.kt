package ft.project.companion.presentation.composables.authentication

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    textFieldColorsCustomConfig: TextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
        unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
        cursorColor = MaterialTheme.colorScheme.onPrimary,
        focusedLabelColor = MaterialTheme.colorScheme.secondary,
        unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
    ),
    textFieldStyleCustomConfig: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .pointerInput(Unit) {
                // here I clear the focus when tapped anywhere else the text field
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(
                25.dp,
                alignment = Alignment.CenterVertically
            ),
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
        ) {
            LoginField(
                state = rememberTextFieldState(),
                modifier = modifier,
                textFieldColorsCustomConfig = textFieldColorsCustomConfig,
                textFieldStyleCustomConfig = textFieldStyleCustomConfig
            )
            PasswordField(
                state = rememberTextFieldState(),
                modifier = modifier,
                textFieldColorsCustomConfig = textFieldColorsCustomConfig,
                textFieldStyleCustomConfig = textFieldStyleCustomConfig,
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            LoginValidationButton(
                onClick = {
                    Log.d("LoginScreen", "Continue Button clicked")
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun LoginField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    textFieldColorsCustomConfig: TextFieldColors,
    textFieldStyleCustomConfig: TextStyle,
) {
    TextField(
        state = state,
        placeholder = { Text(text = "enter your login", style = textFieldStyleCustomConfig) },
        label = { Text(text = "Login", style = textFieldStyleCustomConfig) },
        lineLimits = TextFieldLineLimits.SingleLine,
        textStyle = textFieldStyleCustomConfig,
        colors = textFieldColorsCustomConfig,
        inputTransformation = InputTransformation.maxLength(20),
//        contentPadding = TextFieldDefaults.contentPaddingWithLabel(top = 20.dp),
        modifier = modifier
            .fillMaxWidth(0.8f)
            .wrapContentHeight()
//            .height(80.dp)
//            .paddingFromBaseline(5.dp, 10.dp)
//            .heightIn(0.dp, 70.dp)
    )
}

@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    textFieldColorsCustomConfig: TextFieldColors,
    textFieldStyleCustomConfig: TextStyle,
) {
    var passwordIsVisible by remember { mutableStateOf(false) }

    SecureTextField(
        state = state,
        placeholder = { Text("xxxxxx", style = textFieldStyleCustomConfig) },
        label = { Text("Password", style = textFieldStyleCustomConfig) },
        trailingIcon = {
            if (passwordIsVisible) {
                Icon(
                    imageVector = Icons.Filled.VisibilityOff,
                    contentDescription = "hide password icon",
                    modifier = modifier.clickable { passwordIsVisible = !passwordIsVisible },
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Visibility,
                    contentDescription = "unhide password icon",
                    modifier = modifier.clickable { passwordIsVisible = !passwordIsVisible },
                )
            }
        },
        textObfuscationMode = if (passwordIsVisible) TextObfuscationMode.Visible else TextObfuscationMode.RevealLastTyped,
        textObfuscationCharacter = '*',
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
        ),
        colors = textFieldColorsCustomConfig,
        textStyle = textFieldStyleCustomConfig,
        inputTransformation = InputTransformation.maxLength(42),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .wrapContentHeight()
//            .height(80.dp)
//            .heightIn(0.dp, 70.dp)
    )
}

@Composable
fun LoginValidationButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            disabledContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        modifier = modifier
            .widthIn(240.dp, 280.dp)
            .width(250.dp)
            .heightIn(50.dp, 70.dp)
            .height(55.dp)
    ) {
        Text(text = "Continue", style = MaterialTheme.typography.bodyLarge)
    }
}