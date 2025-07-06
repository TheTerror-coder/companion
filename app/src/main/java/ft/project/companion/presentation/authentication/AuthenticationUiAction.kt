package ft.project.companion.presentation.authentication

sealed class AuthenticationUiAction {
//    object onFortyTwoShieldTap: AuthenticationUiAction()
//    object navigateToHome: AuthenticationUiAction()
//    object navigatedToHome: AuthenticationUiAction()
    object fortyTwoShieldPressed: AuthenticationUiAction()
    object fortyTwoShieldUnpressed: AuthenticationUiAction()
}