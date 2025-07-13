package ft.project.companion.data.datasource.datastore

import android.content.Context
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FortyTwoAuthDataStore(@ApplicationContext val context: Context) {

    private val _accessTokenKey = stringPreferencesKey("access_token")

    /**
     * if no access token exists, saves this one
     * else updates the existing one
     */
    suspend fun updateAccessToken(token: String?){
        try {
            context.companionDataStore.edit{ authDataStore ->
                if (token != null){
                    authDataStore[_accessTokenKey] = token
                }
            }
        } catch (e: IOException){
            // throw something
        }
        catch (e: Exception){
            // throw something
        }
    }

    suspend fun getAccessToken(): Flow<String?> = withContext(Dispatchers.IO){

        context.companionDataStore.data.map { authPreferences ->
            authPreferences[_accessTokenKey]
        }
    }

}