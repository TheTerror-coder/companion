package ft.project.companion.data.datasource.datastore

import ft.project.companion.domain.result.Result
import net.openid.appauth.AuthState

interface AuthDataStore {

    suspend fun saveAuthState(authState: AuthState)
    suspend fun fetchAuthState(): Result<AuthState>

}