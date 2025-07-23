package ft.project.companion.domain.datasource

import ft.project.companion.domain.result.Result
import net.openid.appauth.AuthState

interface AuthDataStore {

    suspend fun saveAuthState(authState: AuthState)
    suspend fun fetchAuthState(): Result<AuthState>

}