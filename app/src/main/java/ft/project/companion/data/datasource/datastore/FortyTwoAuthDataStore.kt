package ft.project.companion.data.datasource.datastore

import android.content.Context
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import net.openid.appauth.AuthState

class FortyTwoAuthDataStore(@ApplicationContext val context: Context) {

    private val _ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")

    /**
     * if no access token exists, saves this one
     * else updates the existing one
     */
    suspend fun updateAccesToken(authState: AuthState){
        try {
            context.companionDataStore.edit{ authDataStore ->
                val accessToken = authState.accessToken
                if (accessToken != null){
                    authDataStore[_ACCESS_TOKEN_KEY] = accessToken
                }
            }
        } catch (e: IOException){
            // throw something
        }
        catch (e: Exception){
            // throw something
        }
    }

    suspend fun getAccesToken(authState: AuthState): Flow<String?>{
        val accessTokenFlow: Flow<String?> = context.companionDataStore.data.map { authPreferences ->
            authPreferences[_ACCESS_TOKEN_KEY]
        }
        return (accessTokenFlow)
    }

}